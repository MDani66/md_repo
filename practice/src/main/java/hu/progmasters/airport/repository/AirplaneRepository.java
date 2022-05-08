package hu.progmasters.airport.repository;

import hu.progmasters.airport.domain.Airplane;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AirplaneRepository {

    private final Map<Integer, Airplane> airplanes = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();


    public Airplane save(Airplane airplaneToSave) {
        int id = idGenerator.incrementAndGet();
        airplaneToSave.setInternalId(id);
        airplanes.put(id, airplaneToSave);
        return airplaneToSave;
    }

    public List<Airplane> listAll() {
        return new ArrayList<>(airplanes.values());
    }

    public Optional<Airplane> findById(int internalId) {
        return airplanes.containsKey(internalId)
                ? Optional.of(airplanes.get(internalId))
                : Optional.empty();
    }
}
