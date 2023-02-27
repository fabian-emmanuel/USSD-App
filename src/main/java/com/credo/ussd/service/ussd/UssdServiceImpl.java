package com.credo.ussd.service.ussd;

import com.credo.ussd.payloads.UssdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UssdServiceImpl implements UssdService {
    // Map to store user sessions
    private final Map<String, String[]> sessions = new HashMap<>();

    @Override
    public String handleRequest(UssdRequest ussdRequest) {

        String text = ussdRequest.getText();
        String sessionId = ussdRequest.getSessionId();
        String serviceCode = ussdRequest.getServiceCode();
//        String phoneNumber = ussdRequest.getMsisdn();
//        String networkCode = ussdRequest.getNetworkCode();
        // Get or create session data
        String[] sessionData = sessions.getOrDefault(sessionId, new String[]{"", ""});
        String menuLevel = sessionData[0];
        String lastResponse = sessionData[1];

        // Handle first menu screen
        if (text.isEmpty()) {
            String response = "CON Hi welcome, I can help you with Ussd Services\n";
            response += "1. Enter 1 to continue";
            lastResponse = response;
            menuLevel = "1";
        }

        // Handle second menu screen
        else if (menuLevel.equals("1") && text.equals("1")) {
            String response = "CON Pick a service below\n";
            response += "1. Create Account \n";
            response += "2. Deposit \n";
            response += "3. Withdraw \n";
            response += "4. Check Balance";
            lastResponse = response;
            menuLevel = "2";
        }

        // Handle service selection
        else if (menuLevel.equals("2") && text.startsWith("1")) {
            // Create Account
            switch (text) {
                case "1" -> {
                    String response = "CON You are about to Create An Account\n";
                    response += "Please enter 1 to confirm";
                    lastResponse = response;
                    menuLevel = "3_1";
                }

                // Deposit
                case "2" -> {
                    String response = "CON You are about to deposit\n";
                    response += "Please enter 1 to confirm";
                    lastResponse = response;
                    menuLevel = "3_2";
                }

                // Withdraw
                case "3" -> {
                    String response = "CON You are about to withdraw\n";
                    response += "Please enter 1 to confirm";
                    lastResponse = response;
                    menuLevel = "3_3";
                }

                // Check Balance
                case "4" -> {
                    String response = "CON You are about to check balance\n";
                    response += "Please enter 1 to confirm";
                    lastResponse = response;
                    menuLevel = "3_4";
                }
            }
        }

        //Handle Service Logic
        else if (menuLevel.startsWith("3")) {
            switch (menuLevel) {
                case "3_1" -> {
                    String response = "CON Enter your first name";
                    response += "Enter your last name";
                    response += "Enter your phone number";
                    response += "Enter your email";
                    response += "Enter a 4-digit pin";
                    lastResponse = response;
                    menuLevel = "4_1";
                }
                case "3_2" -> {
                    String response = "CON Enter amount to deposit";
                    lastResponse = response;
                    menuLevel = "4_2";
                }
                case "3_3" -> {
                    String response = "CON Enter amount to withdraw";
                    lastResponse = response;
                    menuLevel = "4_3";
                }
                case "3_4" -> {
                    String response = "CON Enter your account number";
                    lastResponse = response;
                    menuLevel = "4_4";
                }
            }
        }
        //Todo: Complete the logic for the rest of the menuLevel
        return lastResponse;
    }
}
