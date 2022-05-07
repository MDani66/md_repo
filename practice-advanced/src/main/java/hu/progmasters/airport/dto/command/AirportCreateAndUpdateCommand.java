package hu.progmasters.airport.dto.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportCreateAndUpdateCommand {

    @NotNull(message = "Must not be null!")
    @NotBlank(message = "Must not be blank or empty!")
    private String identifier;

    @NotNull(message = "Must not be null!")
    @NotBlank(message = "Must not be blank or empty!")
    private String name;

    @NotNull(message = "Must not be null!")
    @NotBlank(message = "Must not be blank or empty!")
    private String city;

    @NotNull(message = "Must not be null!")
    @NotBlank(message = "Must not be blank or empty!")
    private String country;

    private boolean deleted;

}
