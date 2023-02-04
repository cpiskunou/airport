package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.service.TicketService;
import by.piskunou.solvdlaba.web.dto.TicketDTO;
import by.piskunou.solvdlaba.web.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;
    private final TicketMapper mapper;

    @GetMapping
    public List<TicketDTO> findAll(@RequestParam("user-id") long userId) {
        return mapper.toDTO( service.findAll(userId) );
    }

    //    @PreAuthorize("hasOwner(#id, #ticket_id) and hasRole('USER')")
    @GetMapping("/{id}")
    public TicketDTO findById(@PathVariable("id") long id, @RequestParam("user-id") long userId) {
        return mapper.toDTO( service.findById(id, userId) );
    }

    //   @PreAuthorize("hasUser(#id) and hasRole('USER')")
    @PostMapping()
    public TicketDTO create(@RequestParam("user-id") long userId, @RequestBody @Validated TicketDTO ticketDTO) {
        Ticket ticket = mapper.toEntity(ticketDTO);
        return mapper.toDTO( service.create(userId, ticket) );
    }

}
