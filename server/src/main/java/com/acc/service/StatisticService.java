package com.acc.service;

import com.acc.model.StatisticResponse;

import java.time.LocalDateTime;

public interface StatisticService {
    /**
     * Данный метод добавляет объекты в статистику
     * @param officeName точка продаж
     * @param amount сумма платежа
     * @param commission сумма комиссии
     * @param dataTime дата
     */
    void addStatistic(String officeName, double amount, double commission, LocalDateTime dataTime);

    /**
     * Данный метод записывает данные в статистику (в HashMap, в данном случае)
     * @return instance StatResponse;
     */
    StatisticResponse collectStatistic();
}
