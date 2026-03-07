package com.example.tradeexplorer.country;

import com.example.tradeexplorer.country.dto.CountryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @GetMapping("/api/countries")
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
