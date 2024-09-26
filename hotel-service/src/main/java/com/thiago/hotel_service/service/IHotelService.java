package com.thiago.hotel_service.service;

import com.thiago.hotel_service.model.Hotel;

import java.util.List;

public interface IHotelService {
    public List<Hotel> getHotelByCityId(Long city_id);

    public void postHotel(Hotel hotel);

    public Hotel getHotelById(Long id);

    public List<Hotel> getAllHotels();

    public void deleteById(Long id);

    public void editHotel(Long id, Hotel hotel);
}
