package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.flights.Flight;
import by.piskunou.solvdlaba.domain.flights.FlightRequest;
import by.piskunou.solvdlaba.domain.flights.FlightResponse;
import by.piskunou.solvdlaba.domain.Seat;

import java.util.List;

public interface FlightService {

    Flight findById(long id);

    Flight create(Flight flight);

    List<FlightResponse> search(FlightRequest flightRequest);

    List<Seat> freeSeats(long id);

    void bookSeat(String number);

    boolean isExists(long id);

}
