package com.esms.country.application;

import com.esms.country.domain.service.CountryService;

public class DeleteCountryUC {
    private CountryService countryService;

    public DeleteCountryUC(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(String id) {
        countryService.deleteCountry(id);
    }

}
