package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.xml.subject.SubjectXml;
import ru.alifba.eksmo.model.dto.xml.subject.SubjectsFileXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.SubjectDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectXmlParser extends AbstractTreeEntityXmlParser<SubjectsFileXml, SubjectXml> {

    private final SubjectDirParser subjectDirParser;

    @Override
    protected DirParser<SubjectsFileXml> getDirParser() {
        return subjectDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getSubjectXmlsPath();
    }

    @Override
    protected List<SubjectXml> getEntityXmlList(SubjectsFileXml fileXml) {
        return fileXml.getSubjects().getSubjects();
    }

}
