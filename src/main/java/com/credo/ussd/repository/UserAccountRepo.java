package com.credo.ussd.repository;

import com.credo.ussd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
}
