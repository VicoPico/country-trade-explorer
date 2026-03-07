package com.example.tradeexplorer.country.repository;

import com.example.tradeexplorer.country.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {
    List<CountryEntity> findAllByOrderByNameAsc();
}
