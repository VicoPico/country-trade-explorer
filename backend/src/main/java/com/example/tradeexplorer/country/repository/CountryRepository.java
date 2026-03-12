package com.example.tradeexplorer.country.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tradeexplorer.country.entity.CountryEntity;

public interface CountryRepository
    extends JpaRepository<CountryEntity, String> {
    List<CountryEntity> findAllByOrderByNameAsc();

    boolean existsByIso3Code(String iso3Code);

    Optional<CountryEntity> findByIso3CodeIgnoreCase(String iso3Code);

    Optional<CountryEntity> findByNumericCode(String numericCode);
}
