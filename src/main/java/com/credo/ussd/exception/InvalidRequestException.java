package com.credo.ussd.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class InvalidRequestException extends RuntimeException{
    private String errorCode;
    private String errorMsg;

    public InvalidRequestException(String errorCode, String errorMsg){
        this.errorCode =errorCode;
        this.errorMsg = errorMsg;
    }

    public InvalidRequestException(String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = "400";
    }
}
