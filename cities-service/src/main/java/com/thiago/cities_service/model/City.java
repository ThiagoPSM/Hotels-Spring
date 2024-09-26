package com.thiago.cities_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long city_id;

    @NotBlank(message ="please add the city name")
    private String name;

    @NotBlank(message ="please add the continent")
    private String continent;

    @NotBlank(message ="please add the country")
    private String country;

    @NotBlank(message ="please add the state")
    private String state;
}
