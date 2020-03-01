package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;

import java.util.Map;

@Service
public interface XmlParser<T> {

    Map<String, T> parse(Config config);

}
