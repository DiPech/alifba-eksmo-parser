package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Category extends TreeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Category parent;
    private List<Category> children;
    private List<Product> products;

    public Category(String guid, String parentGuid, String name) {
        super(guid, parentGuid);
        this.name = name;
    }

}
