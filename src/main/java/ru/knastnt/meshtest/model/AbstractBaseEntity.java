package ru.knastnt.meshtest.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}