package com.example.tradeexplorer.country.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.tradeexplorer.country.repository.CountryRepository;

@Component
public class CountryCodeMapper {

    private final CountryRepository countryRepository;

    public CountryCodeMapper(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Optional<String> toNumericCode(String iso3Code) {
        if (!StringUtils.hasText(iso3Code)) {
            return Optional.empty();
        }

        return countryRepository
            .findByIso3CodeIgnoreCase(iso3Code.trim())
            .map(country -> country.getNumericCode());
    }

    public Optional<String> toIso3CodeFromNumeric(String numericCode) {
        String normalized = normalizeNumericCode(numericCode);
        if (normalized == null) {
            return Optional.empty();
        }

        return countryRepository
            .findByNumericCode(normalized)
            .map(country -> country.getIso3Code());
    }

    static String normalizeNumericCode(String numericCode) {
        if (!StringUtils.hasText(numericCode)) {
            return null;
        }

        String trimmed = numericCode.trim();

        if (!trimmed.chars().allMatch(Character::isDigit)) {
            return null;
        }

        if (trimmed.length() > 3) {
            return null;
        }

        return String.format("%3s", trimmed).replace(' ', '0');
    }
}
