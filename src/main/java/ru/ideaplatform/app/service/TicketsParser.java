package ru.ideaplatform.app.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import ru.ideaplatform.app.mapper.TicketMapper;
import ru.ideaplatform.app.model.Ticket;
import ru.ideaplatform.app.model.TicketsList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class TicketsParser {

    private static final Type TICKET_TYPE = new TypeToken<TicketsList>(){}.getType();
    private final Gson gson = new Gson();
    private final TicketMapper mapper = new TicketMapper();

    public List<Ticket> parseTickets(String fileName) {
        TicketsList tickets = new TicketsList();
        //Чтение файла и парсинг с помощью Gson
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
            if (in == null) {
                throw new FileNotFoundException("Problem with read file.");
            }
            File file = File.createTempFile("temp", null);
            FileUtils.copyInputStreamToFile(in, file);
            JsonReader reader = new JsonReader(new FileReader(file));
            tickets = gson.fromJson(reader, TICKET_TYPE);
        } catch (Exception e) {
            System.out.println("Here is a problem with parsing JSON-file: " + e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return mapper.toTickets(tickets);
    }
}
