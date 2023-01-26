package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.flights.Flight;
import by.piskunou.solvdlaba.domain.Passenger;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {

    @NotNull(message = "Id should be not null")
    private Long id;

    @NotNull(message = "Passenger should be not null")
    private Passenger passenger;

    @NotNull(message = "Flight should be not null")
    private Flight flight;

    @NotNull(message = "Seat should be not null")
    private SeatDTO seat;

}
