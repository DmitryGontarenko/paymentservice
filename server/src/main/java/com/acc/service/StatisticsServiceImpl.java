package com.acc.service;

import com.acc.model.DataStatResponse;
import com.acc.model.OfficeStatResponse;
import com.acc.model.StatisticsObject;
import com.acc.model.StatisticsResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private HashMap<String, StatisticsObject> officeStatisticsHashMap = new HashMap<>();
    private HashMap<LocalDate, StatisticsObject> dateStatisticsHashMap = new HashMap<>();


    @Override
    public void addStatistics(String officeName, double amount, double commission, LocalDateTime dateTime) {
        StatisticsObject officeStatistics = officeStatisticsHashMap
                .getOrDefault(officeName, new StatisticsObject(amount, commission));
        officeStatistics.addStatistics(amount, commission);
        officeStatisticsHashMap.put(officeName, officeStatistics);

        LocalDate localDate = dateTime.toLocalDate();
        StatisticsObject dateStatistics = dateStatisticsHashMap
                .getOrDefault(localDate, new StatisticsObject(amount, commission));
        dateStatistics.addStatistics(amount, commission);
        dateStatisticsHashMap.put(localDate, dateStatistics);

    }

    @Override
    public StatisticsResponse collectStatistics() {
        StatisticsResponse response = new StatisticsResponse();

        officeStatisticsHashMap.forEach((office, v) -> response.getOfficeStatResponsesList().add(
                new OfficeStatResponse(office, v.getNumberOfPayments(), v.getTotalAmount(), v.getTotalCommission())
        ));

        officeStatisticsHashMap = new HashMap<>();


        dateStatisticsHashMap.forEach((date, v) -> response.getDataStatResponseList().add(
                new DataStatResponse(date, v.getNumberOfPayments(), v.getTotalAmount(), v.getTotalCommission())
        ));

        dateStatisticsHashMap = new HashMap<>();

        return response;
    }
}
