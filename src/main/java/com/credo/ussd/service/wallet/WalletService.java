package com.credo.ussd.service.wallet;

import com.credo.ussd.model.User;
import com.credo.ussd.model.Wallet;
import com.credo.ussd.payloads.BaseRequest;

import java.math.BigDecimal;

public interface WalletService {

    Wallet createWallet(BaseRequest baseRequest, User user);

    Wallet deposit(Long userId, BigDecimal amount);
    Wallet withdraw(Long userId, BigDecimal amount);

    BigDecimal getBalance(Long userId);
}
