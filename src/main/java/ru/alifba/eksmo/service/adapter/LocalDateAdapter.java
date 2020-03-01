package ru.alifba.eksmo.service.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate unmarshal(String string) {
        return LocalDate.parse(string, DATE_TIME_FORMATTER);
    }

    public String marshal(LocalDate date) {
        return date.format(DATE_TIME_FORMATTER);
    }

}
