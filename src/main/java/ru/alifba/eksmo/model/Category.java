package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Category implements TreeGuidIdentifiable {

    private final String guid;
    private final String parentGuid;
    private final String name;
    private Category parent;
    private List<Category> children;
    private List<Product> products;

}
