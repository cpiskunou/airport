package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.enums.TicketType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    private Long id;

    private Passenger passenger;

    private Flight flight;

    private BigDecimal price;

    private String seatNumber;

    private TicketType ticketType;
}
