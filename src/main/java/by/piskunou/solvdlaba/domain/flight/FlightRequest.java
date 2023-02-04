package by.piskunou.solvdlaba.domain.flight;

import by.piskunou.solvdlaba.domain.Passenger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightRequest {

    private List<String> fromAirports;
    private List<String> toAirports;
    private List<Passenger> passengers;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

}
