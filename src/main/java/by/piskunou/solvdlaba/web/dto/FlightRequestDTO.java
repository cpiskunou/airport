package by.piskunou.solvdlaba.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FlightRequestDTO {

    @NotNull(message = "Airport from should be not null")
    private String fromAirport;

    private String toAirport;

    @NotNull(message = "Passengers should be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "\\d-\\d-\\d")
    private String passengers;

    @NotNull(message = "Departure time should be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future
    private LocalDate departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future
    private LocalDate arrivalDate;

}
