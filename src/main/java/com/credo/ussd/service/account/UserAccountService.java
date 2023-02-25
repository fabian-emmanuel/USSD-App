package com.credo.ussd.service.account;

import com.credo.ussd.payloads.account.CreateUserAccountRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserAccountService {
    Long createUserAccount(CreateUserAccountRequest createUserAccountRequest, HttpServletRequest request);
}
