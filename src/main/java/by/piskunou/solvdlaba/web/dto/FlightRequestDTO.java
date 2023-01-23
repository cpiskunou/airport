package by.piskunou.solvdlaba.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightRequestDTO {

    @NotNull(message = "Airport from should be not null")
    private List<Long> fromAirports;

    private List<Long> toAirports;

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
