package com.example.tradeexplorer.country.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.tradeexplorer.country.entity.CountryEntity;
import com.example.tradeexplorer.country.repository.CountryRepository;

class CountryCodeMapperTest {

    @Test
    void shouldNormalizeNumericCodesWithLeftPadding() {
        assertEquals("036", CountryCodeMapper.normalizeNumericCode("36"));
        assertEquals("036", CountryCodeMapper.normalizeNumericCode("036"));
        assertEquals("276", CountryCodeMapper.normalizeNumericCode("276"));
        assertNull(CountryCodeMapper.normalizeNumericCode("all"));
        assertNull(CountryCodeMapper.normalizeNumericCode(""));
    }

    @Test
    void shouldMapIso3ToNumericCode() {
        CountryRepository repository = mock(CountryRepository.class);
        CountryCodeMapper mapper = new CountryCodeMapper(repository);

        CountryEntity usa = new CountryEntity("USA", "United States", "840");
        when(repository.findByIso3CodeIgnoreCase("usa"))
            .thenReturn(Optional.of(usa));

        assertEquals(Optional.of("840"), mapper.toNumericCode("usa"));
    }

    @Test
    void shouldMapNumericCodeToIso3WithNormalization() {
        CountryRepository repository = mock(CountryRepository.class);
        CountryCodeMapper mapper = new CountryCodeMapper(repository);

        CountryEntity aus = new CountryEntity("AUS", "Australia", "036");
        when(repository.findByNumericCode("036")).thenReturn(Optional.of(aus));

        assertEquals(Optional.of("AUS"), mapper.toIso3CodeFromNumeric("36"));
    }
}
