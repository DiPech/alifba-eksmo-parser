package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Publisher extends Entity {

    private String name;
    private List<Product> products;

    public Publisher(String guid, String name) {
        super(guid);
        this.name = name;
    }
}
