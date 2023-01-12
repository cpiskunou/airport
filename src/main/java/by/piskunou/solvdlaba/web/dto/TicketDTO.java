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

    @Null(groups = onCreate.class)
    @NotNull(groups = onUpdate.class)
    private Long id;

    @NotNull(groups = onCreate.class)
    private Passenger passenger;

    @NotNull(groups = onCreate.class)
    private Flight flight;

    private BigDecimal price;

    @NotBlank(groups = onCreate.class)
    private SeatDTO seat;

}
