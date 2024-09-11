package com.esms.country.application;

import com.esms.country.domain.entity.Country;
import com.esms.country.domain.service.CountryService;

public class UpdateCountryUC {
    private  CountryService countryService;

    public UpdateCountryUC(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country) {
        countryService.updateCountry(country);
    }

}
