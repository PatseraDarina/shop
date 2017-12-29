package com.epam.preprod.patsera.shop.repository;

public interface IBaseEntity<PK> {
    PK getId();
    void setId(PK id);
}
