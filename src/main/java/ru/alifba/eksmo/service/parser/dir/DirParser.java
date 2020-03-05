package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.alifba.eksmo.util.FileUtils.foreachFile;
import static ru.alifba.eksmo.util.XmlUtils.read;

@Service
public abstract class DirParser<FILE_XML> {

    public List<FILE_XML> parse(Path dir) {
        List<FILE_XML> xmls = new ArrayList<>();
        foreachFile(dir, path -> xmls.add(preprocess(read(path, getXmlClasses()))));
        return xmls;
    }

    protected abstract Class[] getXmlClasses();

    protected FILE_XML preprocess(FILE_XML fileXml) {
        return fileXml;
    }

}
