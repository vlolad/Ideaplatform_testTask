package ru.ideaplatform.app;

import ru.ideaplatform.app.model.Ticket;
import ru.ideaplatform.app.service.Calculator;
import ru.ideaplatform.app.service.TicketsParser;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        //Парсинг значений из json
        TicketsParser parser = new TicketsParser();
        List<Ticket> tickets = parser.parseTickets("tickets.json");
        //Расчет необходимых показателей, с user-friendly выводом
        Calculator calculator = new Calculator();
        calculator.averageFlyTime(tickets);
        calculator.calculatePercentile(tickets, 90);
    }
}
