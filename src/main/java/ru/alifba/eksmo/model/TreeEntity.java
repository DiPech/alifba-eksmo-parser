package ru.alifba.eksmo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class TreeEntity extends Entity implements TreeGuidIdentifiable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Not final, because we can drop the connection with the parent.
     */
    private String parentGuid;

    public TreeEntity(String guid, String parentGuid) {
        super(guid);
        this.parentGuid = parentGuid;
    }

}
