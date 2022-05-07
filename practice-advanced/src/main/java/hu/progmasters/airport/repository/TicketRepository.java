package hu.progmasters.airport.repository;

import hu.progmasters.airport.domain.Ticket;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TicketRepository {

    private final Map<Integer, Ticket> tickets = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();


    public Ticket saveTicket(Ticket ticketToSave) {
        int id = idGenerator.incrementAndGet();
        ticketToSave.setInternalId(id);
        tickets.put(id, ticketToSave);
        return ticketToSave;
    }

    public Optional<Ticket> findById(int internalId) {
        return tickets.containsKey(internalId) ? Optional.of(tickets.get(internalId)) : Optional.empty();
    }

    public List<Ticket> listAll() {
        return new ArrayList<>(tickets.values());
    }

}
