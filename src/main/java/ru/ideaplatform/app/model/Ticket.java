package ru.ideaplatform.app.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
//Основной класс, содержазий сведения о билете
public class Ticket {
    String origin;
    String destination;
    String carrier;
    LocalDateTime departure;
    LocalDateTime arrival;
    int stops;
    long price;

    @ToString.Exclude
    Duration duration;
}
