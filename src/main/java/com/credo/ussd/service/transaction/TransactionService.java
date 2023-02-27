package com.credo.ussd.service.transaction;

import java.math.BigDecimal;

public interface TransactionService {
    void initiateDeposit(Long userId, BigDecimal amount);

    void initiateWithdrawal(Long userId, BigDecimal amount);

    BigDecimal checkBalance(Long userId);
}
