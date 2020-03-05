package ru.alifba.eksmo.service.parser.dir;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.xml.niche.NicheFileXml;
import ru.alifba.eksmo.model.dto.xml.niche.NicheXml;
import ru.alifba.eksmo.model.dto.xml.niche.NichesXml;

@Service
public class NicheDirParser extends DirParser<NicheFileXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{NicheFileXml.class, NichesXml.class, NicheXml.class};
    }

}
