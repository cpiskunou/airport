package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.flights.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private Long id;
    private Passenger passenger;
    private Flight flight;
    private Seat seat;

}
