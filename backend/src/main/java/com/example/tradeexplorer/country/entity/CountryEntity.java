package com.example.tradeexplorer.country.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    @Column(name = "iso3_code", nullable = false, length = 3)
    private String iso3Code;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "numeric_code", nullable = false, length = 3)
    private String numericCode;

    protected CountryEntity() {}

    public CountryEntity(String iso3Code, String name) {
        this(iso3Code, name, null);
    }

    public CountryEntity(String iso3Code, String name, String numericCode) {
        this.iso3Code = iso3Code;
        this.name = name;
        this.numericCode = numericCode;
    }

    public String getIso3Code() {
        return iso3Code;
    }

    public String getName() {
        return name;
    }

    public String getNumericCode() {
        return numericCode;
    }
}
