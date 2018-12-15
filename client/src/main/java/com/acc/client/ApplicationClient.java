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
        // делаем проверку на количество входных параметров
        // их должно быть ровно 4
        if (args.length != 4) {
            log.warn("Please, specify 4 parameters, now there are only {}", args.length);
            return;
        }

        String pathToFileToWrite = args[3]; // 3й аргумент содержит путь файла для записи платежей
        // если по указанному пути нет файла для записи (3й аргумент),
        // то этот файл создается
        if (Files.notExists(Paths.get(pathToFileToWrite))) {
            Files.createFile(Paths.get(pathToFileToWrite));
        }

        RestTemplate template = new RestTemplate();

        // генерируем платеж, первым параметром передаем String, это
        // аргемент 0, который содержит имя файла со списком офисов (и далее преобразуется в коллекцию)
        // втором параметром Long, передаем количество платежей
        paymentService.generatePayment(args[0], Long.valueOf(args[1])).forEach(paymentRequest -> {
            try {
                // аргумент 2 это ссылка на post запрос
                ResponseEntity<PaymentResponse> responseEntity = template.postForEntity(args[2], paymentRequest, PaymentResponse.class);
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    PaymentResponse responseBody = responseEntity.getBody();
                    if (responseBody != null && responseBody.getId() != null) {
                        // обьеденям строки с разделитлем и конвертируем все в стринг
                        String content = String.join(",",
                                                        responseBody.getId(),
                                                        getStringView(paymentRequest.getAmount()),
                                                        paymentRequest.getDateTime().toString(),
                                                        paymentRequest.getOfficeName(),
                                                        getStringView(responseBody.getCommission()));
                        content += "\r\n"; // перевод на новую строку
                        Files.write(
                                Paths.get(args[3]), // путь для записи 3й аргумент
                                content.getBytes(), // кодирует данную строку в последовательность байтов
                                StandardOpenOption.APPEND); // все данные записываются в конец файла
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
