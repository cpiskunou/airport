package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.flights.Flight;
import by.piskunou.solvdlaba.domain.flights.FlightRequest;
import by.piskunou.solvdlaba.service.FlightService;
import by.piskunou.solvdlaba.web.dto.flight.FlightDTO;
import by.piskunou.solvdlaba.web.dto.flight.FlightRequestDTO;
import by.piskunou.solvdlaba.web.dto.flight.FlightResponseDTO;
import by.piskunou.solvdlaba.web.dto.SeatDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.FlightMapper;
import by.piskunou.solvdlaba.web.mapper.FlightRequestMapper;
import by.piskunou.solvdlaba.web.mapper.FlightResponseMapper;
import by.piskunou.solvdlaba.web.mapper.SeatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService  flightService;
    private final FlightMapper flightMapper;
    private final FlightRequestMapper flightRequestMapper;
    private final FlightResponseMapper flightResponseMapper;
    private final SeatMapper seatMapper;

    @GetMapping("/{id}")
    public FlightDTO findById(@PathVariable long id) {
        Flight flight = flightService.findById(id);
        return flightMapper.toDTO(flight);
    }

    @GetMapping("/search")
    public List<FlightResponseDTO> search(@Validated FlightRequestDTO flightRequestDTO) {
        FlightRequest flightRequest = flightRequestMapper.toEntity(flightRequestDTO);
        return flightResponseMapper.toDTO(flightService.search(flightRequest));
    }

    //TODO: write normal with query parameter
    @GetMapping("/{id}/free_seats")
    public List<SeatDTO> findFreeSeats(@PathVariable long id) {
        return seatMapper.toDTO( flightService.freeSeats(id) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
 //   @PreAuthorize("hasRole('ADMIN')")
    public FlightDTO create(@RequestBody @Validated(onCreate.class) FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);

        flight = flightService.create(flight);
        return flightMapper.toDTO(flight);
    }

}
