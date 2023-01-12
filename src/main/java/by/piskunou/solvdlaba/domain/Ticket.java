package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    private Long id;
    private Passenger passenger;
    private Flight flight;
    private BigDecimal price;
    private Seat seat;

}
