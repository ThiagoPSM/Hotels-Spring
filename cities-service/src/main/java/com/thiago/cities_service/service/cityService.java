package com.thiago.cities_service.service;

import com.thiago.cities_service.client.IHotelClient;
import com.thiago.cities_service.dto.cityDTO;
import com.thiago.cities_service.exception.CityNotFoundException;
import com.thiago.cities_service.exception.RequestException;
import com.thiago.cities_service.exception.ResourceNotFoundException;
import com.thiago.cities_service.model.City;
import com.thiago.cities_service.repository.ICityRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class cityService implements ICityService{
    @Autowired
    private ICityRepository cityRepo;
    @Autowired
    private IHotelClient hotelCli;


    @Override
    public City findcity(Long id) {
        Optional<City> city = cityRepo.findById(id);
        if(!city.isPresent()){
            throw new CityNotFoundException("city not found");
        }
        return city.get();
    }

    @Override
    public City findCityByNameAndCountry(String name, String country) {
        Optional<City> city = cityRepo.findCityByNameAndCountry(name, country);
        if(!city.isPresent()){
            throw new CityNotFoundException("city not found");
        }
        return city.get();
    }

    @Override
    public List<City> findAllCitys() {
        List<City> citys = cityRepo.findAll();
        if(citys.isEmpty()){
            throw new ResourceNotFoundException("resource not found");
        }
        return citys;
    }

    @Override
    public void saveCity(City city) {
        Optional<City> cityExist = cityRepo.findCityByName(city.getName());
        if(cityExist.isPresent()){
           throw new RequestException("the city already exist");
        }
        cityRepo.save(city);
    }

    @Override
    public City findCityByName(String name) {
        Optional<City> city = cityRepo.findCityByName(name);
        if(!city.isPresent()){
            throw new CityNotFoundException("city not found");
        }
        return city.get();
    }

    @Override
    public void deleteCity(Long id) {
        Optional<City> cityExist = cityRepo.findById(id);
        if(!cityExist.isPresent()){
            throw new CityNotFoundException("city not found");
        }
        cityRepo.deleteById(id);
    }

    @Override
    public void editCity(Long id, City city) {
        City ct = this.findcity(id);
        ct.setName(city.getName());
        ct.setContinent(city.getContinent());
        ct.setState(city.getState());
        ct.setCountry(city.getCountry());
        cityRepo.save(ct);
    }

    @Override
    @CircuitBreaker(name="hotel-service", fallbackMethod = "fallbackGetCitysHotel")
    public cityDTO getHotelsByCity(String name, String country) {

        Optional<City> city = cityRepo.findCityByNameAndCountry(name, country);

        if(!city.isPresent()){
            throw new CityNotFoundException("city not found");
        }

        cityDTO cityDto = cityDTO.builder()
                .city_id(city.get().getCity_id())
                .name(city.get().getName())
                .continent(city.get().getContinent())
                .country(city.get().getCountry())
                .state(city.get().getState())
                .hotelList(hotelCli.getHotelsByCityId(city.get().getCity_id()))
                .build();
        return cityDto;
    }

    public cityDTO fallbackGetCitysHotel(Throwable throwable){
        return cityDTO.builder()
                .name("Fallback: Service unavailable")
                .city_id(null)
                .continent(null)
                .country(null)
                .state(null)
                .hotelList(null)
                .build();
    }
}
