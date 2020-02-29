package ru.alifba.eksmo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private final Category category;
    private final String guid;
    private final String categoryGuid;
    private final String name;
    private final Float price;
    private final String weight;
    private final Float width;
    private final Float height;
    private final Float depth;
    private final Integer pagesCount;
    private final String image;
    private final String image2;

    @Builder
    public Product(Category category, String guid, String categoryGuid, String name, Float price, String weight,
                   Float width, Float height, Float depth, Integer pagesCount, String image, String image2) {
        this.category = category;
        this.guid = guid;
        this.categoryGuid = categoryGuid;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.pagesCount = pagesCount;
        this.image = image;
        this.image2 = image2;
    }
}
