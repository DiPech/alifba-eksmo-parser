package ru.alifba.eksmo.service.parser.xml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Config;
import ru.alifba.eksmo.model.dto.niche.NicheDto;
import ru.alifba.eksmo.model.dto.niche.NicheXml;
import ru.alifba.eksmo.service.parser.dir.DirParser;
import ru.alifba.eksmo.service.parser.dir.NicheDirParser;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NicheXmlParser extends AbstractTreeEntityXmlParser<NicheXml, NicheDto> {

    private final NicheDirParser nicheDirParser;

    @Override
    protected DirParser<NicheXml> getDirParser() {
        return nicheDirParser;
    }

    @Override
    protected Path getDirPath(Config config) {
        return config.getNicheXmlsPath();
    }

    @Override
    protected List<NicheDto> getDtoList(NicheXml xml) {
        return xml.getNiches().getNiches();
    }

}
