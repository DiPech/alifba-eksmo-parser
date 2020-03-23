package ru.alifba.eksmo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Product extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String categoryGuid;
    private final String publisherGuid;
    private final String name;
    private final String description;
    private final String vendorCode;
    private final String authors;
    private final String isbn;
    private final Float price;
    private final Float weight;
    private final Float width;
    private final Float height;
    private final Float depth;
    private final Integer pagesCount;
    private final String image;
    private final String image2;
    private final LocalDate lastEditionDate;
    private Category category;
    private Publisher publisher;

    @Builder
    public Product(String guid, String categoryGuid, String publisherGuid, String name, String description,
                   String vendorCode, String authors, String isbn, Float price, Float weight, Float width, Float height,
                   Float depth, Integer pagesCount, String image, String image2, LocalDate lastEditionDate,
                   Category category, Publisher publisher) {
        super(guid);
        this.categoryGuid = categoryGuid;
        this.publisherGuid = publisherGuid;
        this.name = name;
        this.description = description;
        this.vendorCode = vendorCode;
        this.authors = authors;
        this.isbn = isbn;
        this.price = price;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.pagesCount = pagesCount;
        this.image = image;
        this.image2 = image2;
        this.lastEditionDate = lastEditionDate;
        this.category = category;
        this.publisher = publisher;
    }

}
