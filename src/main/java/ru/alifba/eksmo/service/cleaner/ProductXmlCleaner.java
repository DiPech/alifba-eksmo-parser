package ru.alifba.eksmo.service.cleaner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.CleanerConfig;
import ru.alifba.eksmo.model.dto.xml.product.ProductXml;
import ru.alifba.eksmo.model.dto.xml.product.ProductsFileXml;
import ru.alifba.eksmo.service.provider.CleanerConfigProvider;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ProductXmlCleaner implements Cleaner<ProductsFileXml> {

    private final CleanerConfigProvider cleanerConfigProvider;

    private CleanerConfig config;

    @PostConstruct
    private void init() {
        config = cleanerConfigProvider.provide();
    }

    public ProductsFileXml clean(ProductsFileXml fileXml) {
        fileXml.getProducts().getProducts().removeIf(this::isNeedToRemoveProduct);
        return fileXml;
    }

    private boolean isNeedToRemoveProduct(ProductXml entityXml) {
        if (entityXml.getPubli() == null || entityXml.getLDateD() == null) {
            return true;
        }
        boolean isPublisherAcceptable = config.getPublishersWhitelist().contains(entityXml.getPubli().getGuid());
        boolean isYearAcceptable = entityXml.getLDateD().getYear() >= config.getMinPublishYear();
        return !isPublisherAcceptable || !isYearAcceptable;
    }

}
