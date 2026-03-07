package com.example.tradeexplorer.country;

import com.example.tradeexplorer.country.dto.CountryResponse;
import com.example.tradeexplorer.country.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/api/countries")
    public List<CountryResponse> getCountries() {
        return countryService.getCountries();
    }
}
