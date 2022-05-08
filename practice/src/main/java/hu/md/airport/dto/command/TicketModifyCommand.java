package hu.progmasters.airport.dto.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TicketModifyCommand {

    @NotBlank(message = "Must not be null, blank or empty!")
    private String name;

    @NotNull(message = "Must not be null!")
    @Email(message = "Invalid email format!")
    private String email;

}
