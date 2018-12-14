package com.acc.service;

import com.acc.model.DataStatResponse;
import com.acc.model.OfficeStatResponse;
import com.acc.model.StatisticObject;
import com.acc.model.StatisticResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class StatisticService {

    private HashMap<String, StatisticObject> officeStatisticHashMap = new HashMap<>();
    private HashMap<LocalDate, StatisticObject> dateStatisticHashMap = new HashMap<>();

    public void addStatistic(double amount,
                             double commission,
                             LocalDateTime dateTime,
                             String officeName) {
        // TODO: synchronized?
        synchronized (this) {
            // getOrDefault вернет указанное значение по умолчанию, если с таким ключем нет
            StatisticObject officeStatistic = officeStatisticHashMap
                    .getOrDefault(officeName, new StatisticObject(amount, commission));
            officeStatistic.addStatistic(amount, commission); // добавляем сумму и комиссию в статистику
            officeStatisticHashMap.put(officeName, officeStatistic);

            LocalDate localDate = dateTime.toLocalDate(); // указываем дату
            StatisticObject dateStatistic = dateStatisticHashMap
                    .getOrDefault(localDate, new StatisticObject(amount, commission));
            dateStatistic.addStatistic(amount, commission);
            dateStatisticHashMap.put(localDate, dateStatistic);
        }
    }

    public StatisticResponse collectionStatistic() {
        synchronized (this) {
            StatisticResponse response = new StatisticResponse();
            // ответ по точку продаж
            officeStatisticHashMap.forEach((office, v) -> response.getOfficeStatResponsesList().add(
                    new OfficeStatResponse(office, v.getNumberOfPayments(), v.getTotalAmount(), v.getTotalCommission())
            ));
            // после запроса статистика очищается
            officeStatisticHashMap = new HashMap<>();

            // ответ по дате
            dateStatisticHashMap.forEach((date, v) -> response.getDataStatResponseList().add(
                    new DataStatResponse(date, v.getNumberOfPayments(), v.getTotalAmount(), v.getTotalCommission())
            ));
            // после запроса статистика очищается
            dateStatisticHashMap = new HashMap<>();

            return response;
        }
    }
}
