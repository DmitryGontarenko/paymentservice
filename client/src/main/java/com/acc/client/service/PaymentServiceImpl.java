package com.acc.client.service;

import com.acc.client.model.Payment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Stream<Payment> generatePayment(String officeFileName, Long numberOfPayments) throws IOException {
        List<String> officeName = Files.lines(Paths.get(officeFileName)).collect(Collectors.toList());
        return LongStream.range(0L, numberOfPayments)
                .mapToObj(e -> new Payment(officeName.get(ThreadLocalRandom.current().nextInt(officeName.size()))));
    }
}
