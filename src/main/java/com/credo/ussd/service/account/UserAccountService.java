package com.credo.ussd.service.account;

import com.credo.ussd.model.User;
import com.credo.ussd.payloads.account.CreateUserAccountRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface UserAccountService {
    Long createUserAccount(CreateUserAccountRequest createUserAccountRequest, HttpServletRequest request);

    Optional<User> findUserById(Long userId);
}
