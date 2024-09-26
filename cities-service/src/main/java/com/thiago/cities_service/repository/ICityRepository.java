package com.thiago.cities_service.repository;

import com.thiago.cities_service.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<City, Long>{
    @Query(value="SELECT * FROM city.city WHERE city.name LIKE :name AND city.country LIKE :country"
    , nativeQuery = true)
    Optional<City> findCityByNameAndCountry(@Param("name")String name, @Param("country")String country);

    @Query(value = "SELECT * FROM city.city WHERE city.name LIKE :name"
    ,nativeQuery = true)
    Optional<City> findCityByName(@Param("name")String name);

}

