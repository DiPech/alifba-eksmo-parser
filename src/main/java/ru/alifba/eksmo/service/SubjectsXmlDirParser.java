package ru.alifba.eksmo.service;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.subject.SubjectDto;
import ru.alifba.eksmo.model.dto.subject.SubjectXml;
import ru.alifba.eksmo.model.dto.subject.SubjectsDto;

@Service
public class SubjectsXmlDirParser extends XmlDirParser<SubjectXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{SubjectXml.class, SubjectsDto.class, SubjectDto.class};
    }
}
