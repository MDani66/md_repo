package hu.progmasters.airport.dto.command;

import hu.progmasters.airport.domain.Producer;
import hu.progmasters.airport.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneCreateAndUpdateCommand {

    @NotNull(message = "Can not be null!")
    @NotBlank(message = "Can not be blank or empty!")
    private String identifier;

    //    @NotNull(message = "Can not be null.")
//    @NotBlank(message = "Can not be blank or empty.")
    private Producer producer;

    //    @NotNull(message = "Can not be null.")
//    @NotBlank(message = "Can not be blank or empty.")
    private Type type;

    @Min(value = 10, message = "Minimum 10!")
    @Max(value = 853, message = "Maximum 853!")
    private int numberOfSeats;

}
