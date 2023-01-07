package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.enums.TicketType;

import java.math.BigDecimal;

public class Ticket {
    private long id;
    private Passenger passenger;
    private User owner;
    private Flight flight;
    private BigDecimal price;
    private String seatNumber;
    private TicketType ticketType;
}
