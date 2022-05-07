package hu.progmasters.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Ticket {

    private int internalId;
    private String name;
    private String email;
    private Flight flight;
    private boolean deleted;

}
