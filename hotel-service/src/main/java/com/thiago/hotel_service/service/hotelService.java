package com.thiago.hotel_service.service;

import com.thiago.hotel_service.exception.hotelNotFoundException;
import com.thiago.hotel_service.exception.requestException;
import com.thiago.hotel_service.exception.resourceNotFoundException;
import com.thiago.hotel_service.model.Hotel;
import com.thiago.hotel_service.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class hotelService implements IHotelService {

    @Autowired
    private IHotelRepository hotelepo;

    @Override
    public void postHotel(Hotel hotel) {
        Optional<Hotel> existHotel = hotelepo.findHotelByName(hotel.getName());
        if(existHotel.isPresent()){
            throw new requestException("hotel already exist");
        }
        hotelepo.save(hotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        Optional<Hotel> hotel = hotelepo.findById(id);
        if(!hotel.isPresent()){
            throw  new hotelNotFoundException("hotel not found");
        }
        return hotel.get();
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = hotelepo.findAll();
        if(hotels.isEmpty()){
            throw new resourceNotFoundException("resource not found");
        }
        return hotels;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Hotel> hotel = hotelepo.findById(id);
        if(!hotel.isPresent()){
            throw new hotelNotFoundException("hotel not found");
        }
        hotelepo.deleteById(id);
    }

    @Override
    public void editHotel(Long id, Hotel hotel) {
        Hotel ht = this.getHotelById(id);

        ht.setName(hotel.getName());
        ht.setStars(hotel.getStars());

        hotelepo.save(ht);
    }

    @Override
    public List<Hotel> getHotelByCityId(Long city_id) {
        List<Hotel> hotel = hotelepo.findHotelByCityId(city_id);
        if(hotel.isEmpty()){
            throw new resourceNotFoundException("resource not found");
        }
       return hotel;
    }
}
