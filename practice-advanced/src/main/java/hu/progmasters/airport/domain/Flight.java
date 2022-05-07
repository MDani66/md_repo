package hu.progmasters.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Flight {

    private int internalId;
    private String identifier;
    private Airplane airplane;
    private Airport startAirport;
    private Airport landingAirport;
    private LocalDateTime timeOfDeparture;
    private LocalDateTime timeOfLanding;
    private int numberOfFreeSeats;
    private List<Integer> ticketIds = new ArrayList<>();
    private boolean deleted;


}
