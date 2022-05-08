package hu.progmasters.airport.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AirplaneNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleAirplaneNotFoundException(AirplaneNotFoundException anfe) {
        ValidationError validationError = new ValidationError("internalId",
                "Airplane with internalId " + anfe.idNotFound() + " can not found!");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException manve) {
        List<ValidationError> validationErrors = manve.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightTimeNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleFlightTimeNotValidException(FlightTimeNotValidException ftnve) {
        ValidationError validationError = new ValidationError("identifier",
                "Flight with identifier " + ftnve.getIdentifier()
                        + " cant be saved, check the times of departure and landing!");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleAirportNotFoundException(AirportNotFoundException anfe) {
        ValidationError validationError = new ValidationError("internalId",
                "Airport with internalId " + anfe.idNotFound() + " can not found!");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleFlightNotFoundException(FlightNotFoundException fnfe) {
        ValidationError validationError = new ValidationError("internalId",
                "Flight with internalId " + fnfe.idNotFound() + " can not found!");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AirplaneIsFullException.class)
    public ResponseEntity<List<ValidationError>> handleAirplaneIsFullException(AirplaneIsFullException aife) {
        ValidationError validationError = new ValidationError("internalId",
                "Can not book a ticket for flight with internalId " + aife.getFlightId() +
                        " because it's full.");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleTicketNotFoundException(TicketNotFoundException tnfe) {
        ValidationError validationError = new ValidationError("internalId",
                "Ticket with internal id " + tnfe.idNotFound() + " can not found!");
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

}
