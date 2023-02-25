package com.credo.ussd.payloads.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {
    @JsonProperty(value = "bank_name")
    String bankName;

    @JsonProperty(value = "account_number")
    String accountNumber;

    @JsonProperty(value = "amount")
    BigDecimal amount;
}
