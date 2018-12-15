package com.acc.controller;

import com.acc.model.PaymentRequest;
import com.acc.model.PaymentResponse;
import com.acc.model.StatisticResponse;
import com.acc.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ServerController {

    @Autowired
    private StatisticService statisticService;

    @PostMapping("/pay")
    public
    PaymentResponse payResp(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(UUID.randomUUID().toString()); // устанавливем рандомное значение идентификатора в ответ о платеже
        paymentResponse.setCommission(paymentRequest.getAmount()); // комиссия равна 0.15% от полученной суммы

        statisticService.addStatistic(paymentRequest.getOfficeName(),
                                        paymentRequest.getAmount(),
                                        paymentResponse.getCommission(),
                                        paymentRequest.getDateTime());
        return paymentResponse;
    }

    @GetMapping("/stat")
    public StatisticResponse getStat() {
        return statisticService.collectStatistic();
    }



}
