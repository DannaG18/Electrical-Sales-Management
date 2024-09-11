package com.esms.city.application;

import com.esms.city.domain.service.CityService;

public class DeleteCityUC {
    private final CityService cityService;

    public DeleteCityUC(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute (String id) {
        cityService.deleteCity(id);
    }
}
