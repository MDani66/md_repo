package hu.progmasters.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Airport {

    private int internalId;
    private String identifier;
    private String name;
    private String city;
    private String country;
    private boolean deleted;

}
