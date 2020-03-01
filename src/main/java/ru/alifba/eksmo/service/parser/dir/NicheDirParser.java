package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.niche.NicheDto;
import ru.alifba.eksmo.model.dto.niche.NicheXml;
import ru.alifba.eksmo.model.dto.niche.NichesDto;

@Service
public class NicheDirParser extends DirParser<NicheXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{NicheXml.class, NichesDto.class, NicheDto.class};
    }

}
