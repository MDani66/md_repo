package hu.progmasters.airport.exceptionhandling;

public class FlightTimeNotValidException extends RuntimeException {

    private final String identifier;

    public FlightTimeNotValidException(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
