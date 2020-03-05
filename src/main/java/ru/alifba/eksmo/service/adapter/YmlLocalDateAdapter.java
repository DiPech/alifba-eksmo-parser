package ru.alifba.eksmo.service.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class YmlLocalDateAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public LocalDateTime unmarshal(String string) {
        return LocalDateTime.parse(string, DATE_TIME_FORMATTER);
    }

    public String marshal(LocalDateTime datetime) {
        return datetime.format(DATE_TIME_FORMATTER);
    }

}
