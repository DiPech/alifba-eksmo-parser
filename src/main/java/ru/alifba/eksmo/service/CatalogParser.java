package ru.alifba.eksmo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Catalog;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.Product;
import ru.alifba.eksmo.model.dto.product.ProductDto;
import ru.alifba.eksmo.model.dto.product.ProductXml;
import ru.alifba.eksmo.model.dto.subject.SubjectXml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogParser {

    private final ProductsXmlDirParser productsXmlDirParser;
    private final SubjectsXmlDirParser subjectsXmlDirParser;

    public Catalog parse(Config config) {
        List<SubjectXml> subjectXmls = subjectsXmlDirParser.parse(config.getSubjectXmlsPath());
        List<ProductXml> productXmls = productsXmlDirParser.parse(config.getProductXmlsPath());
        Map<String, Category> categoryMap = getCategoryMap(subjectXmls);
        Map<String, Product> productMap = getProductMap(productXmls);
        fillCategoriesWithProducts(categoryMap, productMap);
        return new Catalog(categoryMap, productMap);
    }

    private Map<String, Category> getCategoryMap(List<SubjectXml> xmls) {
        Map<String, Category> categoryMap = buildCategoryWithoutParentsMap(xmls);
        fillParentCategories(categoryMap);
        return categoryMap;
    }

    private void fillParentCategories(Map<String, Category> categoryMap) {
        categoryMap.forEach((guid, category) -> {
            if (categoryMap.containsKey(category.getParentGuid())) {
                category.setParent(categoryMap.get(category.getParentGuid()));
            }
        });
    }

    private Map<String, Category> buildCategoryWithoutParentsMap(List<SubjectXml> xmls) {
        Map<String, Category> hashMap = new HashMap<>();
        xmls.forEach(xml -> xml.getSubjects().getSubjects().forEach(dto -> {
            Category category = new Category(dto.getGuid(), dto.getParentGuid(), dto.getName());
            hashMap.put(dto.getGuid(), category);
        }));
        return hashMap;
    }

    private void fillCategoriesWithProducts(Map<String, Category> categoryMap, Map<String, Product> productMap) {
        categoryMap.forEach((guid, category) -> {
            List<Product> categoryProducts = productMap.values().stream()
                .filter(product -> product.getCategoryGuid().equals(guid))
                .collect(Collectors.toList());
            category.setProducts(categoryProducts);
        });
    }

    private Map<String, Product> getProductMap(List<ProductXml> xmls) {
        Map<String, Product> hashMap = new HashMap<>();
        xmls.forEach(xml -> xml.getProducts().getProducts().forEach(dto -> {
            hashMap.put(dto.getGuid(), productFromDto(dto));
        }));
        return hashMap;
    }

    private Product productFromDto(ProductDto dto) {
        return Product.builder()
            .guid(dto.getGuid())
            .categoryGuid(dto.getSbjct().getGuid())
            .name(dto.getName())
            .price(dto.getPrice())
            .weight(dto.getBrgew())
            .width(dto.getWidth())
            .height(dto.getHeight())
            .depth(dto.getDepth())
            .image(dto.getSourcePicture())
            .image2(dto.getSourceCover4())
            .pagesCount(dto.getQtypg())
            .build();
    }

}
