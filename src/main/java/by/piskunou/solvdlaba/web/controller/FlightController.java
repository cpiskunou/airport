package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.domain.FlightRequest;
import by.piskunou.solvdlaba.service.FlightService;
import by.piskunou.solvdlaba.web.dto.FlightDTO;
import by.piskunou.solvdlaba.web.dto.FlightRequestDTO;
import by.piskunou.solvdlaba.web.dto.FlightResponseDTO;
import by.piskunou.solvdlaba.web.dto.SeatDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.FlightMapper;
import by.piskunou.solvdlaba.web.mapper.FlightRequestMapper;
import by.piskunou.solvdlaba.web.mapper.FlightResponseMapper;
import by.piskunou.solvdlaba.web.mapper.SeatMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
@Validated
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
    public List<FlightResponseDTO> search(@Valid FlightRequestDTO flightRequestDTO) {

        FlightRequest flightRequest = flightRequestMapper.toEntity(flightRequestDTO);

        return flightResponseMapper.toDTO(flightService.search(flightRequest));
    }

    @GetMapping("/{id}/free_seats")
    public List<SeatDTO> findFreeSeats(@PathVariable long id) {

        return seatMapper.toDTO(flightService.freeSeats(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(onCreate.class)
    public FlightDTO create(@RequestBody @Valid FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);

        flight = flightService.create(flight);

        return flightMapper.toDTO(flight);
    }

}
