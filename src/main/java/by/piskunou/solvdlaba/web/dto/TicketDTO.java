package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.domain.Passenger;
import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {

    @NotNull(groups = onUpdate.class, message = "Id should be not null")
    private Long id;

    @NotNull(groups = onUpdate.class, message = "Passenger should be not null")
    private Passenger passenger;

    @NotNull(groups = onUpdate.class, message = "Flight should be not null")
    private Flight flight;

    @NotNull(groups = onUpdate.class, message = "Seat should be not null")
    private SeatDTO seat;

}
