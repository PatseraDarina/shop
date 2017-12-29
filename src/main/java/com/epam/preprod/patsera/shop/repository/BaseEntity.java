package com.epam.preprod.patsera.shop.repository;

public abstract class BaseEntity<PK> implements IBaseEntity<PK> {

    protected PK id;

    @Override
    public PK getId() {
        return null;
    }

    @Override
    public void setId(PK id) {

    }
}
