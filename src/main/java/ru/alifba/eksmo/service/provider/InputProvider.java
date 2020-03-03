package ru.alifba.eksmo.service.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InputProvider {

    private final Environment env;

    public String getString(String name, String defaultValue) {
        String value = env.getProperty(name, defaultValue);
        Objects.requireNonNull(value, name + " cannot be null");
        return value;
    }

    public String getString(String name) {
        return getString(name, null);
    }

    public Integer getInteger(String name) {
        String value = getString(name, null);
        return Integer.parseInt(value);
    }

}
