package hu.progmasters.airport.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightModifyCommand {

    @Future(message = "Must be a future date!")
    @NotNull(message = "Can not be null!")
    private LocalDateTime timeOfDeparture;

    @Future(message = "Must be a future date!")
    @NotNull(message = "Can not be null!")
    private LocalDateTime timeOfLanding;

}
