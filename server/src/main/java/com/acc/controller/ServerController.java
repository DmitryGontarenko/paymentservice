package com.acc.controller;

import com.acc.model.PaymentRequest;
import com.acc.model.PaymentResponse;
import com.acc.model.StatisticsResponse;
import com.acc.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ServerController {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("/pay")
    public
    PaymentResponse payResponse(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse(paymentRequest.getAmount());
        statisticsService.addStatistics(paymentRequest.getOfficeName(),
                                        paymentRequest.getAmount(),
                                        paymentResponse.getCommission(),
                                        paymentRequest.getDateTime());
        return paymentResponse;
    }

    @GetMapping("/stat")
    public StatisticsResponse getStatistics() {
        return statisticsService.collectStatistics();
    }



}
