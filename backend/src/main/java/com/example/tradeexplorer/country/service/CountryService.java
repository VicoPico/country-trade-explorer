package com.example.tradeexplorer.country.service;

import com.example.tradeexplorer.country.dto.CountryResponse;
import com.example.tradeexplorer.country.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryResponse> getCountries() {
        return countryRepository.findAllByOrderByNameAsc()
                .stream()
                .map(country -> new CountryResponse(
                        country.getIso3Code(),
                        country.getName()
                ))
                .toList();
    }
}
