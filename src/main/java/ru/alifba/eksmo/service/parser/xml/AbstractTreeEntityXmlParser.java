package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.dto.xml.TreeEntityXml;

@Service
public abstract class AbstractTreeEntityXmlParser<FILE_XML, ENTITY_XML extends TreeEntityXml>
    extends AbstractXmlParser<FILE_XML, ENTITY_XML, Category> {

    @Override
    protected Category convertEntityXmlToEntity(ENTITY_XML entityXml) {
        return new Category(entityXml.getGuid(), entityXml.getParentGuid(), entityXml.getName());
    }

}
