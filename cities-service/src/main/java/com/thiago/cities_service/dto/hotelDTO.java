package com.thiago.cities_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class hotelDTO {
    private Long id;
    private String name;
    private Long stars;
    private Long city_id;
}
