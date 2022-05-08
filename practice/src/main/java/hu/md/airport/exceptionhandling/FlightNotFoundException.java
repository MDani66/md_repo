package hu.progmasters.airport.exceptionhandling;

public class FlightNotFoundException extends RuntimeException {

    private int idNotFound;


    public FlightNotFoundException(int idNotFound) {
        this.idNotFound = idNotFound;
    }

    public int idNotFound() {
        return idNotFound;
    }
}
