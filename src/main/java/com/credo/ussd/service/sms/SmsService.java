package com.credo.ussd.service.sms;

import com.credo.ussd.model.User;

public interface SmsService {
    void sendSmsNotification(User user);
}
