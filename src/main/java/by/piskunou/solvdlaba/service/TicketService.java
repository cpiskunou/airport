package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Ticket;

public interface TicketService {

    Ticket appointOwner(Ticket ticket, long userId);
    
}
