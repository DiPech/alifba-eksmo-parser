package ru.alifba.eksmo.util;

import lombok.experimental.UtilityClass;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Path;

import static ru.alifba.eksmo.util.ThrowingRunnable.throwingRunnable;
import static ru.alifba.eksmo.util.ThrowingSupplier.throwingSupplier;

@UtilityClass
public class XmlUtils {

    @SuppressWarnings("unchecked")
    public static <T> T read(Path path, Class... classes) {
        return throwingSupplier(() -> {
            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(path.toFile());
        });
    }

    public static <T> void write(T t, Path path, Class... classes) {
        throwingRunnable(() -> {
            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(t, path.toFile());
        });
    }

}
