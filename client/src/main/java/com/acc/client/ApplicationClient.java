package com.acc.client;

import com.acc.client.model.PaymentResponse;
import com.acc.client.service.PaymentService;
import com.acc.client.validation.ValidationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@SpringBootApplication
public class ApplicationClient implements CommandLineRunner {

    @Autowired
    private PaymentService paymentService;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationClient.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ValidationClient.validationArgs(args);

        String pathToFileToWrite = args[3];
        if (Files.notExists(Paths.get(pathToFileToWrite))) {
            Files.createFile(Paths.get(pathToFileToWrite));
        }

        RestTemplate template = new RestTemplate();


        paymentService.generatePayment(args[0], Long.valueOf(args[1])).forEach(paymentRequest -> {
            try {

                ResponseEntity<PaymentResponse> responseEntity = template.postForEntity(args[2], paymentRequest, PaymentResponse.class);
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    PaymentResponse responseBody = responseEntity.getBody();
                    if (responseBody != null && responseBody.getId() != null) {

                        String content = String.join(",",
                                                        responseBody.getId(),
                                                        getStringView(paymentRequest.getAmount()),
                                                        paymentRequest.getDateTime().toString(),
                                                        paymentRequest.getOfficeName(),
                                                        getStringView(responseBody.getCommission()));
                        content += "\r\n";
                        Files.write(
                                Paths.get(args[3]),
                                content.getBytes(),
                                StandardOpenOption.APPEND);
                    }
                }

            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });

    }

    private String getStringView(double number) {
        return String.format("%.2f", number);
    }
}
