package ru.alifba.eksmo.model;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catalog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Category> categories;
    private Map<String, Product> products;
    private Map<String, Publisher> publishers;

}
