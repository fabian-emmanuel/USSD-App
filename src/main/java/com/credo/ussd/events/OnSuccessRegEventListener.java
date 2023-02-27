package com.credo.ussd.events;

import com.credo.ussd.service.sms.SmsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Data
public class OnSuccessRegEventListener implements ApplicationListener<OnSuccessRegEvent> {
    private final SmsService smsService;

    @Override
    public void onApplicationEvent(OnSuccessRegEvent event) {
        smsService.sendSmsNotification(event.getUser(), event.getWallet());
        log.info("Sms Notification Has Been Sent}");
    }
}
