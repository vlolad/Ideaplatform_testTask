package ru.ideaplatform.app.service;

import ru.ideaplatform.app.model.Ticket;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {

    //Расчет среднего времени полета среди полученных билетов
    public void averageFlyTime(List<Ticket> data) {
        int count = 0;
        Duration time = Duration.ZERO;
        for (Ticket ticket : data) {
            count++;
            time = time.plus(ticket.getDuration());
        }
        long avgTime = time.toMillis() / count;

        System.out.println("Среднее время полета составляет: " + DateTimeFormatter.ofPattern("HH часов mm минут")
                .withZone(ZoneOffset.UTC)
                .format(Instant.ofEpochMilli(avgTime)));
    }

    //Расчет требуемого перцентиля среди полученных билетов
    public void calculatePercentile(List<Ticket> data, double percentile) {
        List<Long> values = new ArrayList<>();
        for (Ticket ticket : data) {
            values.add(ticket.getDuration().toMillis());
        }
        Collections.sort(values);

        int index = (int) Math.ceil(percentile / 100.0 * values.size());
        long result = values.get(index);

        System.out.println("Заданный процентиль " + percentile + "% составляет: "
                + DateTimeFormatter.ofPattern("HH часов mm минут")
                .withZone(ZoneOffset.UTC)
                .format(Instant.ofEpochMilli(result)));
    }
}
