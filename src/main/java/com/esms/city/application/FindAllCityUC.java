package com.esms.city.application;

import java.util.List;

import com.esms.city.domain.entity.City;
import com.esms.city.domain.service.CityService;

public class FindAllCityUC {
    private final CityService cityService;

    public FindAllCityUC(CityService cityService) {
        this.cityService = cityService;
    }

    public List<City> execute() {
        return cityService.findAllCity();
    }
}
