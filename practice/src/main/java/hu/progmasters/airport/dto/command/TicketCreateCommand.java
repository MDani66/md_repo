package hu.progmasters.airport.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreateCommand {

    @NotBlank(message = "Must not be null, blank or empty!")
    private String name;

    @NotNull(message = "Must not be null!")
    @Email(message = "Invalid email format!")
    private String email;

    private int flightId;

}
