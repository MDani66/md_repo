package hu.progmasters.airport.exceptionhandling;

public class AirplaneIsFullException extends RuntimeException {

    private final int flightId;


    public AirplaneIsFullException(int flightId) {
        this.flightId = flightId;
    }

    public int getFlightId() {
        return flightId;
    }
}
