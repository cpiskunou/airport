package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.domain.Seat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    Optional<Flight> findById(long id);

    void create(Flight flight);

    List<Flight> search(Long[] fromAirports, Long[] toAirports, int passengerAmount,
                        LocalDateTime start, LocalDateTime end);

    List<Seat> freeSeats(long id);

}
