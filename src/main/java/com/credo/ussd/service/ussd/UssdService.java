package com.credo.ussd.service.ussd;

import com.credo.ussd.payloads.UssdRequest;

public interface UssdService {
    String handleRequest(UssdRequest ussdRequest);
}
