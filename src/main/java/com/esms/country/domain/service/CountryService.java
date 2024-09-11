package com.esms.country.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.country.domain.entity.Country;

public interface CountryService {
    void createCountry (Country country);
    void deleteCountry (String id);
    Optional <Country> findCountry (String id);
    void updateCountry (Country country);
    List <Country> findAllCountry();
}
