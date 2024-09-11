package com.esms.city.application;

import com.esms.city.domain.entity.City;
import com.esms.city.domain.service.CityService;

public class CreateCityUC {
    private final CityService cityService;

    public CreateCityUC(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city) {
        cityService.createCity(city);
    }

}
