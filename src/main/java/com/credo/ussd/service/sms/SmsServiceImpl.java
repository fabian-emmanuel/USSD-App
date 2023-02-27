package com.credo.ussd.service.sms;

import com.credo.ussd.model.User;
import com.credo.ussd.model.Wallet;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;
    @Value("${twilio.msgSid}")
    private String msgSid;

    @Override
    public void sendSmsNotification(User user, Wallet wallet) {
        Twilio.init(accountSid, authToken);
        String messageBody = String.format("%nWelcome to %s! %nYour account number is %s!", wallet.getBankName(), wallet.getAccountNumber());
        try {
            Message message = Message.creator(
                    new PhoneNumber(user.getPhoneNumber()),
                    msgSid,
                    messageBody
            ).create();
            log.info("Message Sent ==> {}", message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
