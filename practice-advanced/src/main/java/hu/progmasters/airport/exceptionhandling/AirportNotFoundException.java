package hu.progmasters.airport.exceptionhandling;

public class AirportNotFoundException extends RuntimeException {

    private final int idNotFound;

    public AirportNotFoundException(int idNotFound) {
        this.idNotFound = idNotFound;
    }

    public int idNotFound() {
        return idNotFound;
    }
}
