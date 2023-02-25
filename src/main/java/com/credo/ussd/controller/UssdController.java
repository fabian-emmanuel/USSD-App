package com.credo.ussd.controller;

import com.credo.ussd.payloads.UssdRequest;
import com.credo.ussd.service.ussd.UssdService;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Value
public class UssdController {

    UssdService ussdService;
    @PostMapping("/ussd")
    public ResponseEntity<String> handleUssdRequest(@RequestBody UssdRequest ussdRequest) {
        return ResponseEntity.ok(this.ussdService.handleRequest(ussdRequest));
    }
}
