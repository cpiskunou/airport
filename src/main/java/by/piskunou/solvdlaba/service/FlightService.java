package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.web.dto.FlightRequest;

import java.util.List;

public interface FlightService {

    Flight findById(long id);

    Flight create(Flight flight);

    List<Flight> search(FlightRequest flightRequest);
}
