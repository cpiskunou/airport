package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistent.TicketRepository;
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
            passportService.create(ticket.getPassenger().getPassport());
        }
        flightService.bookSeat(ticket.getSeat().getNumber());
        ticketRepository.create(ticket, userId);
        return ticket;
    }

}
