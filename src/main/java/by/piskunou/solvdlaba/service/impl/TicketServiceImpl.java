package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Passport;
import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.TicketRepository;
import by.piskunou.solvdlaba.service.FlightService;
import by.piskunou.solvdlaba.service.PassengerService;
import by.piskunou.solvdlaba.service.PassportService;
import by.piskunou.solvdlaba.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final FlightService flightService;
    private final PassengerService passengerService;
    private final TicketRepository ticketRepository;
    private final PassportService passportService;

    @Override
    @Transactional
    public Ticket appointOwner(Ticket ticket, long userId) {
        if(!flightService.isExists(ticket.getFlight().getId())) {
            throw new ResourceNotExistsException("There's no flight with such id");
        }
        if(!passengerService.isExists(ticket.getPassenger().getId())) {
            passengerService.create(ticket.getPassenger());

            Passport passport = ticket.getPassenger().getPassport();
            passport.setId(ticket.getPassenger().getId());

            passportService.create(passport);
        }
        flightService.bookSeat(ticket.getSeat().getPlace());
        ticketRepository.create(ticket, userId);
        return ticket;
    }

    @Override
    @Transactional
    public Ticket findById(long id) {
        return ticketRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotExistsException("There's no ticket with such id"));
    }

    @Override
    public boolean isOwner(long ticketId, long userId) {
        return ticketRepository.isOwner(ticketId, userId);
    }

}
