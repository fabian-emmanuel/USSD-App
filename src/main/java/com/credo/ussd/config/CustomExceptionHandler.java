package com.credo.ussd.config;

import com.credo.ussd.exception.InvalidRequestException;
import com.credo.ussd.payloads.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<BaseResponse<?>> handleInvalidRequest(InvalidRequestException ex){
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.BAD_REQUEST.name(), ex.getErrorMsg()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(new BaseResponse<>(HttpStatus.BAD_REQUEST.name(), String.valueOf(errors)));
    }
}
