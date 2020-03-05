package ru.alifba.eksmo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Entity implements GuidIdentifiable, Serializable {

    private static final long serialVersionUID = 1L;

    private String guid;

}
