package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class Catalog {

    private final Map<String, Category> categories;
    private final Map<String, Product> products;

}
