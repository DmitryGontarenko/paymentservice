package com.acc.service;

import com.acc.model.StatisticsResponse;

import java.time.LocalDateTime;

public interface StatisticsService {
    /**
     * Данный метод добавляет объекты в статистику
     * @param officeName точка продаж
     * @param amount сумма платежа
     * @param commission сумма комиссии
     * @param dateTime дата
     */
    void addStatistics(String officeName, double amount, double commission, LocalDateTime dateTime);

    /**
     * Данный метод отображает объекты статистики
     * @return instance StatResponse;
     */
    StatisticsResponse collectStatistics();
}
