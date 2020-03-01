package ru.alifba.eksmo.service.parser.xml;

import org.springframework.stereotype.Service;
import ru.alifba.eksmo.model.Category;
import ru.alifba.eksmo.model.dto.TreeDto;

@Service
public abstract class AbstractTreeEntityXmlParser<XML, DTO extends TreeDto>
    extends AbstractXmlParser<XML, DTO, Category> {

    @Override
    protected Category convertDtoToEntity(DTO dto) {
        return new Category(dto.getGuid(), dto.getParentGuid(), dto.getName());
    }

}
