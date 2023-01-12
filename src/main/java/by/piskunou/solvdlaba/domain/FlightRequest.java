package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightRequest {

    private Long[] fromAirports;
    private Long[] toAirports;
    private List<Passenger> passengers;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

}
