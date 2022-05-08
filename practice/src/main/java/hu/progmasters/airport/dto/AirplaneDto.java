package hu.progmasters.airport.dto;

import hu.progmasters.airport.domain.Producer;
import hu.progmasters.airport.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AirplaneDto {

    private int internalId;
    private String identifier;
    private Producer producer;
    private Type type;
    private int numberOfSeats;

}
