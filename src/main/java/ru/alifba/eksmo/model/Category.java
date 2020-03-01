package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category extends TreeEntity {

    private String name;
    private Category parent;
    private List<Category> children;
    private List<Product> products;

    public Category(String guid, String parentGuid, String name) {
        super(guid, parentGuid);
        this.name = name;
    }
}