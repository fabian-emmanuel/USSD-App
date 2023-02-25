package com.credo.ussd.service.ussd;

import com.credo.ussd.payloads.UssdRequest;
import com.credo.ussd.payloads.UssdResponse;
import org.springframework.stereotype.Service;

@Service
public class UssdServiceImpl implements UssdService {
    private static final int STATE_MAIN_MENU = 0;
    private static final int STATE_CREATE_ACCOUNT = 1;
    private static final int STATE_DEPOSIT_AMOUNT = 2;
    private static final int STATE_WITHDRAW_AMOUNT = 3;
    private static final int STATE_CHECK_BALANCE = 4;

    private int currentState = STATE_MAIN_MENU;
    private String accountNumber;
    private double balance = 0.0;
    private String name;
    private String email;
    private String phone;

    @Override
    public String handleRequest(UssdRequest request) {
        String input = request.getText().trim();
        String response = "";
        String sessionId = request.getSessionId();

        if (input.equals("*123#")){
            return "Select an option:\n1. Create account\n2. Deposit\n3. Withdraw\n4. Check balance";
        }

        return switch (input) {
            case "1" -> createAccount(sessionId);
            case "2" -> deposit(sessionId);
            case "3" -> withdraw(sessionId);
            case "4" -> checkBalance(sessionId);
            default -> "Invalid input. Please try again.";
        };
    }

    public String createAccount(String sessionId) {
        // TODO: Implement logic for creating an account
        return "Account created successfully.";
    }

    public String deposit(String sessionId) {
        // TODO: Implement logic for depositing funds
        return "Funds deposited successfully.";
    }

    public String withdraw(String sessionId) {
        // TODO: Implement logic for withdrawing funds
        return "Funds withdrawn successfully.";
    }

    public String checkBalance(String sessionId) {
        // TODO: Implement logic for checking account balance
        return "Your balance is $100.00";
    }
}
