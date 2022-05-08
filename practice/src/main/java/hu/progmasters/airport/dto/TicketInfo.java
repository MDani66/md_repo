package hu.progmasters.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketInfo {

    private int internalId;
    private String name;
    private String email;
    private FlightInfo flight;

}
