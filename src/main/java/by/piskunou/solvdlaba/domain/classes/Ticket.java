package by.piskunou.solvdlaba.domain.classes;

import by.piskunou.solvdlaba.domain.enums.TicketType;

public class Ticket {
    private long id;
    private Flight departureFlight;
    private Flight returnFlight;
    private long price;
    private TicketType ticketType;
    private Person owner;
}
