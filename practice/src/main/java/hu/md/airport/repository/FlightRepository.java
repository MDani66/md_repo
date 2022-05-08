package hu.progmasters.airport.repository;

import hu.progmasters.airport.domain.Flight;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class FlightRepository {

    private final Map<Integer, Flight> flights = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();


    public Flight saveFlight(Flight flightToSave) {
        int id = idGenerator.incrementAndGet();
        flightToSave.setInternalId(id);
        flights.put(id, flightToSave);
        return flightToSave;
    }

    public Optional<Flight> findByInternalId(int internalId) {
        return flights.containsKey(internalId) ? Optional.of(flights.get(internalId)) : Optional.empty();
    }

    public List<Flight> listAll() {
        return new ArrayList<>(flights.values());
    }
}
