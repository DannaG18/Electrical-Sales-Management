package com.esms.country.application;

import com.esms.country.domain.entity.Country;
import com.esms.country.domain.service.CountryService;

public class CreateCountryUC {
    private CountryService  countryService;

    public CreateCountryUC(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country) {
        countryService.createCountry(country);
    }
}
