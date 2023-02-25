package com.credo.ussd.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String status;
    private String message;
    private T data;

    public BaseResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
