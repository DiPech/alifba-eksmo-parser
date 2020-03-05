package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.xml.niche.NicheXml;
import ru.alifba.eksmo.model.dto.xml.niche.NicheFileXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.NicheDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NicheXmlParser extends AbstractTreeEntityXmlParser<NicheFileXml, NicheXml> {

    private final NicheDirParser nicheDirParser;

    @Override
    protected DirParser<NicheFileXml> getDirParser() {
        return nicheDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getNicheXmlsPath();
    }

    @Override
    protected List<NicheXml> getEntityXmlList(NicheFileXml fileXml) {
        return fileXml.getNiches().getNiches();
    }

}
