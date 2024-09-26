package com.thiago.cities_service.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class cityDTO {
    private Long city_id;
    private String name;
    private String continent;
    private String country;
    private String state;
    private List<hotelDTO> hotelList;
}

