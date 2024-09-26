package com.thiago.cities_service.service;

import com.thiago.cities_service.dto.cityDTO;
import com.thiago.cities_service.model.City;

import java.util.List;

public interface ICityService {
    public City findcity(Long id);
    public City findCityByNameAndCountry(String name, String country);
    public City findCityByName(String name);
    public List<City> findAllCitys();
    public void saveCity(City city);
    public void deleteCity(Long id);
    public void editCity(Long id, City city);
    public cityDTO getHotelsByCity(String name, String country);
}
