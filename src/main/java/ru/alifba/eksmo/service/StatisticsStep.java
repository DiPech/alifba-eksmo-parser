package ru.alifba.eksmo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.Product;
import ru.alifba.eksmo.model.dto.Products;
import ru.alifba.eksmo.model.dto.Xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import static ru.alifba.eksmo.util.ThrowingRunnable.throwingRunnable;

@Service
@Qualifier("statistics")
public class StatisticsStep implements Step {

    public void execute(Config config) {
        throwingRunnable(() -> {
            JAXBContext jaxbContext = JAXBContext.newInstance(Xml.class, Products.class, Product.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Xml xml = (Xml) unmarshaller.unmarshal(config.getInputDir().resolve("1.xml").toFile());
            System.out.println(xml);
        });
    }

}
