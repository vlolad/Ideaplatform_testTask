package ru.ideaplatform.app.mapper;

import ru.ideaplatform.app.model.Ticket;
import ru.ideaplatform.app.model.TicketData;
import ru.ideaplatform.app.model.TicketsList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TicketMapper {

    //Т.к. некоторые даты в нестандартном формате, в форматтере два паттерна
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd.MM.yy HH:mm][dd.MM.yy H:mm]");

    public List<Ticket> toTickets(TicketsList list) {
        return list.getTickets().stream().map(this::toTicket).collect(Collectors.toList());
    }

    //Сторки, которые не требутся непосредственно для поставленной задачи, немного упрощаются
    public Ticket toTicket(TicketData data) {
        Ticket ticket = Ticket.builder()
                .origin(data.getOrigin() + " | " + data.getOrigin_name())
                .destination(data.getDestination() + " | " + data.getDestination_name())
                .carrier(data.getCarrier())
                .departure(parseTime(data.getDeparture_date(), data.getDeparture_time()))
                .arrival(parseTime(data.getArrival_date(), data.getArrival_time()))
                .stops(data.getStops())
                .price(data.getPrice())
                .build();

        ticket.setDuration(Duration.between(ticket.getDeparture(), ticket.getArrival()));

        return ticket;
    }

    private LocalDateTime parseTime(String date, String time) {
        return LocalDateTime.parse(date + " " + time, formatter);
    }
}
