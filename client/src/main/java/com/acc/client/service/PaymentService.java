package com.acc.client.service;

import com.acc.client.model.Payment;

import java.io.IOException;
import java.util.stream.Stream;

public interface PaymentService {
    /**
     * Данный метод возвращает сгенерированный платеж
     * @param officeFileName наименование офиса
     * @param numberOfPayments номер платежа
     * @return объект типа LongStream
     * @throws IOException
     */
    Stream<Payment> generatePayment(String officeFileName, Long numberOfPayments) throws IOException;

}
