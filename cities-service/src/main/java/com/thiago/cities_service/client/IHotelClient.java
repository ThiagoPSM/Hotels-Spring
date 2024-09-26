package com.thiago.cities_service.client;

import com.thiago.cities_service.dto.hotelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="hotel-service")
public interface IHotelClient {

    @GetMapping("hotels/{city_id}")
    public List<hotelDTO> getHotelsByCityId(@PathVariable Long city_id);
}
