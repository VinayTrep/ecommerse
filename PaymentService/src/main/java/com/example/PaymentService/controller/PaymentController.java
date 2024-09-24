package com.example.PaymentService.controller;

import com.example.PaymentService.dto.CreatePaymentLinkRequestDto;
import com.example.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<String> generatePaymentLink(@RequestBody CreatePaymentLinkRequestDto requestDto) {
        //verify user and order Id
        System.out.println("request received: " + requestDto);
        return ResponseEntity.ok(paymentService.generatePayment(requestDto));
    }


}
