package com.credo.ussd.service.wallet;

import com.credo.ussd.model.User;
import com.credo.ussd.payloads.BaseRequest;

public interface WalletService {

    void createWallet(BaseRequest baseRequest, User user);
}
