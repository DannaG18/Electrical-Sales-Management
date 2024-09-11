package com.esms.country.application;

import java.util.List;

import com.esms.country.domain.entity.Country;
import com.esms.country.domain.service.CountryService;

public class FindAllCountryUC {
    private final CountryService countryService;

    public FindAllCountryUC(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<Country> execute() {
        return countryService.findAllCountry();
    }
}
