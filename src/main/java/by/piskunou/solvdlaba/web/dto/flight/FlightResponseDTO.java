package by.piskunou.solvdlaba.web.dto.flight;

import by.piskunou.solvdlaba.domain.flights.Flight;

import java.math.BigDecimal;

public class FlightResponseDTO {

    public Flight toFlight;
    public Flight backFlight;
    public BigDecimal price;

}
