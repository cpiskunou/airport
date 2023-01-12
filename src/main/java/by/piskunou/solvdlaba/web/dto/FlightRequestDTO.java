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

    @NotNull
    private String fromAirport;
    private String toAirport;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "\\d-\\d-\\d")
    private String passengers;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future
    private LocalDate departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

}
