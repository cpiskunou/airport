package by.piskunou.solvdlaba.web.dto;

import java.time.LocalDate;

public class FlightRequest {

    private CityDTO fromCity;
    private CityDTO toCity;

    private AirportDTO fromAirport;
    private AirportDTO toAirport;

    private AirlineDTO airline;

    private LocalDate departureDate;
}
