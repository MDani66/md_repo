package hu.progmasters.airport.exceptionhandling;

public class TicketNotFoundException extends RuntimeException {

    private final int idNotFound;

    public TicketNotFoundException(int idNotFound) {
        this.idNotFound = idNotFound;
    }

    public int idNotFound() {
        return idNotFound;
    }
}
