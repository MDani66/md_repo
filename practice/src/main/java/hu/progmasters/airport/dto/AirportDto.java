package hu.progmasters.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AirportDto {

    private int internalId;
    private String identifier;
    private String name;
    private String city;
    private String country;

}
