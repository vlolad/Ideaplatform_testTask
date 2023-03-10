package ru.ideaplatform.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
//Класс, который парсится с JSON-файла
public class TicketsList {
    private List<TicketData> tickets;
}
