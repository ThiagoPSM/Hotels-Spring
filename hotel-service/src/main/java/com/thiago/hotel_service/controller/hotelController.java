package com.thiago.hotel_service.controller;

import com.thiago.hotel_service.model.Hotel;
import com.thiago.hotel_service.service.IHotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class hotelController {

    @Autowired
    private IHotelService hotelServ;

    @GetMapping("/{city_id}")
    public List<Hotel> getHotelsByCityId(@PathVariable Long city_id){
        return hotelServ.getHotelByCityId(city_id);
    }

    @GetMapping("/id/{id}")
    public Hotel getHotalById(@PathVariable Long id){
        return hotelServ.getHotelById(id);
    }

    @GetMapping("")
    public List<Hotel> getAllHotels(){
        return hotelServ.getAllHotels();
    }

    @PostMapping("/post")
    public ResponseEntity<String> postHotel(@Valid @RequestBody Hotel hotel){
        hotelServ.postHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body("successfully created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHotelById(@PathVariable Long id){
        hotelServ.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("successfully removed");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editHotal(@Valid @RequestBody Hotel hotel, @PathVariable Long id){
        hotelServ.editHotel(id, hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body("successfully edited");
    }
}
