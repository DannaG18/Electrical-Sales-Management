package com.esms.country.application;

import java.util.Optional;

import com.esms.country.domain.entity.Country;
import com.esms.country.domain.service.CountryService;

public class FindCountryUC {
    private CountryService countryService;

    public FindCountryUC(CountryService countryService) {
        this.countryService = countryService;
    }

    public Optional<Country> execute(String id) {
        return countryService.findCountry(id);
    }
}
