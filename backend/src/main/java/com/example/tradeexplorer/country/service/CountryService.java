package com.example.tradeexplorer.country.service;

import com.example.tradeexplorer.country.dto.CountryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    public List<CountryResponse> getCountries() {
        return List.of(
                new CountryResponse("BRA", "Brazil"),
                new CountryResponse("CHN", "China"),
                new CountryResponse("DEU", "Germany"),
                new CountryResponse("FRA", "France"),
                new CountryResponse("GBR", "United Kingdom"),
                new CountryResponse("IND", "India"),
                new CountryResponse("JPN", "Japan"),
                new CountryResponse("SWE", "Sweden"),
                new CountryResponse("USA", "United States")
        );
    }
}
