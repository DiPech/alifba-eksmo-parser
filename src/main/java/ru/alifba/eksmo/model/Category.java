package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category implements TreeGuidIdentifiable {

    private final String guid;
    private String parentGuid;
    private String name;
    private Category parent;
    private List<Category> children;
    private List<Product> products;

    public Category(String guid, String parentGuid, String name) {
        this.guid = guid;
        this.parentGuid = parentGuid;
        this.name = name;
    }
}
