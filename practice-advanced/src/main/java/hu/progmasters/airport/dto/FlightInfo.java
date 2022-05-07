package hu.progmasters.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightInfo {

    private int internalId;
    private String identifier;
    private AirplaneDto airplane;
    private AirportDto startAirport;
    private AirportDto landingAirport;
    private LocalDateTime timeOfDeparture;
    private LocalDateTime timeOfLanding;
    private int numberOfFreeSeats;

}
