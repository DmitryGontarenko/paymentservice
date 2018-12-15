package com.acc.client.service;

import com.acc.client.model.Payment;

import java.io.IOException;
import java.util.stream.Stream;

public interface PaymentService {
    Stream<Payment> generatePayment(String officeFileName, Long numberOfPayments) throws IOException;

}
