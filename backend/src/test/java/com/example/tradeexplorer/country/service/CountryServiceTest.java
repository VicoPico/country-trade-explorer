package com.example.tradeexplorer.country.service;

import com.example.tradeexplorer.country.dto.CountryResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest {

    private final CountryService countryService = new CountryService();

    @Test
    void shouldReturnCountryList() {
        List<CountryResponse> countries = countryService.getCountries();

        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertTrue(countries.size() >= 5);
    }

    @Test
    void shouldContainSwedenAndUnitedStates() {
        List<CountryResponse> countries = countryService.getCountries();

        assertTrue(countries.stream().anyMatch(country -> "SWE".equals(country.code())));
        assertTrue(countries.stream().anyMatch(country -> "USA".equals(country.code())));
    }
}
