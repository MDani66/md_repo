package hu.progmasters.airport.exceptionhandling;

public class AirplaneNotFoundException extends RuntimeException {

    private final int idNotFound;

    public AirplaneNotFoundException(int idNotFound) {
        this.idNotFound = idNotFound;
    }

    public int idNotFound() {
        return idNotFound;
    }
}
