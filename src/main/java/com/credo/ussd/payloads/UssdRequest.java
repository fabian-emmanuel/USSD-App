package com.credo.ussd.payloads;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
public class UssdRequest {
    String sessionId;
    String serviceCode;
    String networkCode;
    String msisdn;
    String text;

}
