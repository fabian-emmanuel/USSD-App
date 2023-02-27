package com.credo.ussd.service.sms;

import com.credo.ussd.model.User;
import com.credo.ussd.model.Wallet;

public interface SmsService {
    void sendSmsNotification(User user, Wallet wallet);
}
