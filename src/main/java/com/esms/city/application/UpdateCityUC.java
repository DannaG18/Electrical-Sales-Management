package com.esms.city.application;

import com.esms.city.domain.entity.City;
import com.esms.city.domain.service.CityService;

public class UpdateCityUC {
    private final CityService cityService;

    public UpdateCityUC(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute (City city) {
        cityService.updateCity(city);
    }
}
