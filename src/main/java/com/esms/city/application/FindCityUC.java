package com.esms.city.application;

import java.util.Optional;

import com.esms.city.domain.entity.City;
import com.esms.city.domain.service.CityService;

public class FindCityUC {
    private final CityService cityService;

    public FindCityUC(CityService cityService) {
        this.cityService = cityService;
    }

    public Optional<City> execute(String id) {
        return cityService.findCity(id);
    }
}
