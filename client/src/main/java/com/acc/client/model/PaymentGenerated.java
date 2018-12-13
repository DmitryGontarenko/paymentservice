package com.acc.client.model;

import java.time.*;
import java.util.concurrent.ThreadLocalRandom;


public class PaymentGenerated {

    private static final long START_OF_LAST_YEAR = LocalDateTime.of(Year.now().getValue() - 1, Month.JANUARY, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();
    private static final long START_OF_CURRENT_YEAR = LocalDateTime.of(Year.now().getValue(), Month.JANUARY, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();
    private static final double MIN_SUM = 10.000;
    private static final double MAX_SUM = 100.000;

    public static LocalDateTime generatedRandomDate() {
        long randomDateInEpochSeconds = ThreadLocalRandom.current().nextLong(START_OF_LAST_YEAR, START_OF_CURRENT_YEAR);
        return Instant.ofEpochSecond(randomDateInEpochSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static double generatedRandomSum() {
        return ThreadLocalRandom.current().nextDouble(MIN_SUM, MAX_SUM);
    }
}
