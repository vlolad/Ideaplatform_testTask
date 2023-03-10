package ru.ideaplatform.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//Класс, считывающий строки из Json-файла. Далее маппится в Ticket.class
public class TicketData {

    String origin;
    String origin_name;
    String destination;
    String destination_name;
    String departure_date;
    String departure_time;
    String arrival_date;
    String arrival_time;
    String carrier;
    int stops;
    long price;

}
