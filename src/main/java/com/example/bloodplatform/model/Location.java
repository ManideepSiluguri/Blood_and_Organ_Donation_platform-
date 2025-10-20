package com.example.bloodplatform.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    private String address;
    private String city;
    private String state;
    private String country;
    private Double latitude;
    private Double longitude;
}
