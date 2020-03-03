package ru.alifba.eksmo.service.cleaner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.CleanerConfig;
import ru.alifba.eksmo.model.dto.product.ProductDto;
import ru.alifba.eksmo.model.dto.product.ProductXml;
import ru.alifba.eksmo.service.provider.CleanerConfigProvider;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ProductXmlCleaner implements Cleaner<ProductXml> {

    private final CleanerConfigProvider cleanerConfigProvider;

    private CleanerConfig config;

    @PostConstruct
    private void init() {
        config = cleanerConfigProvider.provide();
    }

    public ProductXml clean(ProductXml xml) {
        xml.getProducts().getProducts().removeIf(this::isNeedToRemoveProduct);
        return xml;
    }

    private boolean isNeedToRemoveProduct(ProductDto productDto) {
        if (productDto.getPubli() == null || productDto.getLDateD() == null) {
            return true;
        }
        boolean isPublisherAcceptable = config.getPublishersWhitelist().contains(productDto.getPubli().getGuid());
        boolean isYearAcceptable = productDto.getLDateD().getYear() >= config.getMinPublishYear();
        return !isPublisherAcceptable || !isYearAcceptable;
    }

}
