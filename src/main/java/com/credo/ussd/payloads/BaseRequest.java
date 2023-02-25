package com.credo.ussd.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseRequest {
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3, message = "Name can not be less than 3")
    private String name;
    @NotEmpty
    @Size(min = 11, max = 11, message = "BVN number must not be less or greater than 11")
    private String bvn;
    @NotEmpty
    @Size(min = 11, message = "Phone number should have at least 11 characters")
    private String phoneNumber;
}
