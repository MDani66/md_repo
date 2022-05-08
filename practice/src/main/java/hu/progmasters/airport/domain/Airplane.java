package hu.progmasters.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Airplane {

    private int internalId;
    private String identifier;
    private Producer producer;
    private Type type;
    private int numberOfSeats;
    private boolean deleted;

}
