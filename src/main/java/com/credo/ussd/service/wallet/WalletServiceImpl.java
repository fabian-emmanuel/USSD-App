package com.credo.ussd.service.wallet;

import com.credo.ussd.client.FlutterWaveClient;
import com.credo.ussd.exception.InvalidRequestException;
import com.credo.ussd.model.User;
import com.credo.ussd.model.Wallet;
import com.credo.ussd.payloads.BaseRequest;
import com.credo.ussd.payloads.wallet.FlwBaseRequest;
import com.credo.ussd.repository.WalletRepo;
import com.credo.ussd.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    private final FlutterWaveClient flutterWaveClient;
    private final WalletRepo walletRepo;
    private final ObjectMapper mapper;


    @Override
    public Wallet createWallet(BaseRequest baseRequest, User user) {
        var response = flutterWaveClient.createWallet(generatePayload(baseRequest)).block();
        try {
            if (response != null) {
                JsonNode rootNode = mapper.readTree(mapper.writeValueAsString(response.getData()));
                log.info("JsonNode =======> {}", rootNode);
                Wallet wallet = Wallet.builder()
                        .user(user)
                        .bankName(rootNode.get("bank_name").asText())
                        .balance(rootNode.get("amount").decimalValue())
                        .accountNumber(rootNode.get("account_number").asText())
                        .txRef(rootNode.get("flw_ref").asText())
                        .build();
               return this.walletRepo.save(wallet);
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Wallet deposit(Long userId, BigDecimal amount) {
      Wallet wallet = this.walletRepo.findWalletByUser_Id(userId)
              .orElseThrow(() -> new InvalidRequestException("No Wallet Found For User -> User Is Not Registered!"));

      return this.updateWallet(wallet, amount);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Wallet withdraw(Long userId, BigDecimal transferAmount) {
        Wallet wallet = this.walletRepo.findWalletByUser_Id(userId)
                .orElseThrow(() -> new InvalidRequestException("No Wallet Found For User -> User Is Not Registered!"));

        if(wallet.getBalance().compareTo(transferAmount) < 0){
            throw new InvalidRequestException("Insufficient Funds!");
        }

        wallet.setBalance(wallet.getBalance().subtract(transferAmount));
        return wallet;
    }

    @Override
    public BigDecimal getBalance(Long userId) {
        Wallet wallet = this.walletRepo.findWalletByUser_Id(userId)
                .orElseThrow(() -> new InvalidRequestException("No Wallet Found For User -> User Is Not Registered!"));

        return wallet.getBalance();
    }

    private Wallet updateWallet(Wallet wallet, BigDecimal amount) {
        log.info("Wallet Balance before funding -> {}", wallet.getBalance());
        wallet.setBalance(wallet.getBalance().add(amount));
        log.info("Wallet Balance after funding -> {}",wallet.getBalance());
        return wallet;
    }

    private FlwBaseRequest generatePayload(BaseRequest baseRequest) {
        String firstName = baseRequest.getName().split(" ")[0];
        String lastName = baseRequest.getName().split(" ")[1];
        return FlwBaseRequest
                .builder()
                .email(baseRequest.getEmail())
                .firstName(firstName)
                .lastName(lastName)
                .bvn(baseRequest.getBvn())
                .phoneNumber(baseRequest.getPhoneNumber())
                .narration(String.format("%s %s", firstName, lastName))
                .is_permanent(true)
                .txRef(Util.generateUniqueCode())
                .build();
    }
}
