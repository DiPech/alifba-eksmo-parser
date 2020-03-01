package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.alifba.eksmo.util.ThrowingRunnable.throwingRunnable;

@Service
public abstract class DirParser<XML> {

    @SuppressWarnings("unchecked")
    public List<XML> parse(Path dir) {
        List<XML> xmls = new ArrayList<>();
        throwingRunnable(() -> {
            JAXBContext jaxbContext = JAXBContext.newInstance(xmlClasses());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Files.list(dir).forEach(filePath -> throwingRunnable(
                () -> xmls.add((XML) unmarshaller.unmarshal(filePath.toFile()))
            ));
        });
        return xmls;
    }

    protected abstract Class[] xmlClasses();

}
