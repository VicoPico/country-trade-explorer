package com.example.tradeexplorer.country;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CountryController {

    @GetMapping("/api/countries")
    public List<Map<String, String>> getCountries() {
        return List.of(
                Map.of("code", "BRA", "name", "Brazil"),
                Map.of("code", "CHN", "name", "China"),
                Map.of("code", "DEU", "name", "Germany"),
                Map.of("code", "FRA", "name", "France"),
                Map.of("code", "GBR", "name", "United Kingdom"),
                Map.of("code", "IND", "name", "India"),
                Map.of("code", "JPN", "name", "Japan"),
                Map.of("code", "SWE", "name", "Sweden"),
                Map.of("code", "USA", "name", "United States")
        );
    }
}
