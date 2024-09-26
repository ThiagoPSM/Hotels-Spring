package com.thiago.hotel_service.repository;

import com.thiago.hotel_service.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Long>{

    @Query(value="SELECT * FROM hotel.hotel WHERE hotel.hotel.city_id LIKE :city_id"
            ,nativeQuery = true)
    List<Hotel> findHotelByCityId(@Param("city_id") Long city_id);

    @Query(value = "SELECT * FROM hotel.hotel WHERE hotel.hotel.name LIKE :name"
            ,nativeQuery = true)
    Optional<Hotel> findHotelByName(@Param("name") String name);
}
