package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TreeEntity extends Entity implements TreeGuidIdentifiable {

    /**
     * Not final, because we can drop the connection with the parent.
     */
    private String parentGuid;

    public TreeEntity(String guid, String parentGuid) {
        super(guid);
        this.parentGuid = parentGuid;
    }
}
