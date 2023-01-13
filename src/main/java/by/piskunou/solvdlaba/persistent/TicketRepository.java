package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Ticket;

public interface TicketRepository {

    void create(Ticket ticket, long userId);

}
