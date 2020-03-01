package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.subject.SubjectDto;
import ru.alifba.eksmo.model.dto.subject.SubjectXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.SubjectDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectXmlParser extends AbstractTreeEntityXmlParser<SubjectXml, SubjectDto> {

    private final SubjectDirParser subjectDirParser;

    @Override
    protected DirParser<SubjectXml> getDirParser() {
        return subjectDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getSubjectXmlsPath();
    }

    @Override
    protected List<SubjectDto> getDtoList(SubjectXml xml) {
        return xml.getSubjects().getSubjects();
    }

}
