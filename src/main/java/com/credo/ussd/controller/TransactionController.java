package com.credo.ussd.controller;

import com.credo.ussd.payloads.BaseResponse;
import com.credo.ussd.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/deposit/{userId}")
    public ResponseEntity<BaseResponse<?>> fundWallet(@PathVariable Long userId, BigDecimal amount){
        this.transactionService.initiateDeposit(userId, amount);
        return ResponseEntity.ok(new BaseResponse<>(OK.name(), "Deposit Successful!"));
    }

    @PostMapping("/withdraw/{userId}")
    public ResponseEntity<BaseResponse<?>> withdraw(@PathVariable Long userId, BigDecimal amount){
        this.transactionService.initiateWithdrawal(userId, amount);
        return ResponseEntity.ok(new BaseResponse<>(OK.name(), "Withdrawal Successful!"));
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BaseResponse<BigDecimal>> checkBalance(@PathVariable Long userId){
        BigDecimal balance = this.transactionService.checkBalance(userId);
        return ResponseEntity.ok(new BaseResponse<>(OK.name(), "Balance Retrived Successfully!", balance));
    }
}
