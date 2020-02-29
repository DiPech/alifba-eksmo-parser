package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.dto.niche.NicheDto;
import ru.alifba.eksmo.model.dto.niche.NicheXml;
import ru.alifba.eksmo.model.dto.niche.NichesDto;

@Service
public class NicheXmlParser extends XmlParser<NicheXml> {

    @Override
    protected Class[] xmlClasses() {
        return new Class[]{NicheXml.class, NichesDto.class, NicheDto.class};
    }

}
