package com.credo.ussd.service.wallet;

import com.credo.ussd.client.FlutterWaveClient;
import com.credo.ussd.model.User;
import com.credo.ussd.model.Wallet;
import com.credo.ussd.payloads.BaseRequest;
import com.credo.ussd.payloads.wallet.FlwBaseRequest;
import com.credo.ussd.repository.WalletRepo;
import com.credo.ussd.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Value
@Slf4j
public class WalletServiceImpl implements WalletService {
    FlutterWaveClient flutterWaveClient;
    WalletRepo walletRepo;
    ObjectMapper mapper;


    @Override
    public void createWallet(BaseRequest baseRequest, User user) {
        var response = flutterWaveClient.createWallet(generatePayload(baseRequest)).block();
        try {
            if (response != null) {
                JsonNode rootNode = mapper.readTree(mapper.writeValueAsString(response.getData()));
                Wallet wallet = Wallet.builder()
                        .user(user)
                        .bankName(rootNode.get("bank_name").asText())
                        .balance(rootNode.get("amount").decimalValue())
                        .accountNumber(rootNode.get("account_number").asText())
                        .txRef(rootNode.get("flw_ref").asText())
                        .build();
                this.walletRepo.save(wallet);
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
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
