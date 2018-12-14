package com.acc.client;

import com.acc.client.model.PaymentResponse;
import com.acc.client.service.PaymentService;
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
        if (args.length != 4) {
            log.warn("Was specified {} parameters, you need 4 for to run", args.length);
            return;
        }

        String pathToFileToWrite = args[3]; // 3й аргумент передает файл, который содержит информацию о записи платежах
        // если файла по указанному пути нет, то файл создается
        if (Files.notExists(Paths.get(pathToFileToWrite))) {
            Files.createFile(Paths.get(pathToFileToWrite));
        }

        RestTemplate template = new RestTemplate();

        // 0й аргументом передаем текстовый файл со списком офисов
        // 1й аргемент указывает количество платежей
        paymentService.generatePayment(args[0], Long.valueOf(args[1])).parallel().forEach(paymentRequest -> {
            try {
                // 2й аргумент передает url, куда будут отправленны данные о платежах
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
                        content += "\r\n"; // перевод на новую строку
                        Files.write(
                                Paths.get(args[3]), // путь файла
                                content.getBytes(), // кодирует данную строку в последовательность байтов
                                StandardOpenOption.APPEND); // байты записываются в конец файла
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
