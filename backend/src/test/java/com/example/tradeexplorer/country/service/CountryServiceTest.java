package com.example.tradeexplorer.country.service;

import com.example.tradeexplorer.country.dto.CountryResponse;
import com.example.tradeexplorer.country.entity.CountryEntity;
import com.example.tradeexplorer.country.repository.CountryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CountryServiceTest {

    @Test
    void shouldReturnCountriesFromRepository() {
        CountryRepository repository = mock(CountryRepository.class);
        CountryService countryService = new CountryService(repository);

        when(repository.findAllByOrderByNameAsc()).thenReturn(List.of(
                new CountryEntity("SWE", "Sweden"),
                new CountryEntity("USA", "United States")
        ));

        List<CountryResponse> countries = countryService.getCountries();

        assertNotNull(countries);
        assertEquals(2, countries.size());
        assertEquals("SWE", countries.get(0).code());
        assertEquals("Sweden", countries.get(0).name());
        assertEquals("USA", countries.get(1).code());

        verify(repository).findAllByOrderByNameAsc();
    }
}
