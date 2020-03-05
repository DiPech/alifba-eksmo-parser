package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.xml.subject.SubjectXml;
import ru.alifba.eksmo.model.dto.xml.subject.SubjectsFileXml;
import ru.alifba.eksmo.model.dto.xml.subject.SubjectsXml;

@Service
public class SubjectDirParser extends DirParser<SubjectsFileXml> {

    @Override
    protected Class[] getXmlClasses() {
        return new Class[]{SubjectsFileXml.class, SubjectsXml.class, SubjectXml.class};
    }

}
