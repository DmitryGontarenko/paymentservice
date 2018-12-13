package com.acc.controller;

import com.acc.model.PaymentRequest;
import com.acc.model.PaymentResponse;
import com.acc.model.StatisticResponse;
import com.acc.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

@RestController
public class ServerController {

    @Autowired
    private StatisticService statisticService;

    @PostMapping("/pay")
    public @ResponseBody
    PaymentResponse payResp(@RequestBody PaymentRequest paymentRequest) {

        PaymentResponse paymentResponse = new PaymentResponse(); // экземпляр ответа о платеже
        paymentResponse.setId(UUID.randomUUID().toString()); // устанавливем рандомное значение идентификатора в ответ о платеже
        double commission = paymentRequest.getAmount() * 0.0015d; // считаем комиссию
        paymentResponse.setCommission(commission); // устанавливаем комиссию в 0.015% в ответ о платеже

        statisticService.addStatistic(paymentRequest.getAmount(),
                                      commission,
                                      paymentRequest.getDateTime(),
                                      paymentRequest.getOfficeName());
        return paymentResponse;
    }

    @GetMapping("/stat")
    public @ResponseBody
    StatisticResponse getStat() {
        return statisticService.collectionStatistic();
    }



}
