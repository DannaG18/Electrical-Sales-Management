package com.esms.city.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.city.domain.entity.City;

public interface CityService {
    void createCity (City city);
    void deleteCity (String id);
    Optional <City> findCity (String id);
    void updateCity (City city);
    List <City> findAllCity();
}
