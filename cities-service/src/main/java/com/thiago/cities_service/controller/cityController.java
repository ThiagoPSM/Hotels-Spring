package com.thiago.cities_service.controller;

import com.thiago.cities_service.dto.cityDTO;
import com.thiago.cities_service.model.City;
import com.thiago.cities_service.service.ICityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citys")
public class cityController {

    @Autowired
    private ICityService cityServ;

    @GetMapping("/hotels/{name}/{country}")
    public cityDTO getHotelsByCity(@PathVariable String name, @PathVariable String country){
        return cityServ.getHotelsByCity(name, country);
    }

    @GetMapping("/{name}/{country}")
    public City getCityByCityId(@PathVariable String name, @PathVariable String country){
        return cityServ.findCityByNameAndCountry(name, country);
    }

    @GetMapping("/id/{id}")
    public City findCityById(@PathVariable Long id){
        return cityServ.findcity(id);
    }

    @GetMapping("/name/{name}")
    public City findCityByName(@PathVariable String name){
        return cityServ.findCityByName(name);
    }

    @GetMapping("")
    public List<City> findAllCitys(){
        return cityServ.findAllCitys();
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveCity(@Valid @RequestBody City city){
        cityServ.saveCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body("successfully created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCityById(@PathVariable Long id){
        cityServ.deleteCity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("successfully removed");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editCityById(@PathVariable Long id, @Valid @RequestBody City city){
        cityServ.editCity(id, city);
        return ResponseEntity.status(HttpStatus.CREATED).body("successfully edited");
    }
}
