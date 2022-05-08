package hu.progmasters.airport.repository;

import hu.progmasters.airport.domain.Airport;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AirportRepository {

    private final Map<Integer, Airport> airports = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();


    public Airport saveAirport(Airport airportToSave) {
        int id = idGenerator.incrementAndGet();
        airportToSave.setInternalId(id);
        airports.put(id, airportToSave);
        return airportToSave;
    }

    public Optional<Airport> findByInternalId(int internalId) {
        return airports.containsKey(internalId)
                ? Optional.of(airports.get(internalId))
                : Optional.empty();
    }

    public List<Airport> listAll() {
        return new ArrayList<>(airports.values());
    }

    public void delete(int internalId) {
        airports.get(internalId).setDeleted(true);
    }
}
