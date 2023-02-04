package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.flight.Flight;
import by.piskunou.solvdlaba.domain.flight.FlightRequest;
import by.piskunou.solvdlaba.service.FlightService;
import by.piskunou.solvdlaba.web.dto.flight.FlightDTO;
import by.piskunou.solvdlaba.web.dto.flight.FlightRequestDTO;
import by.piskunou.solvdlaba.web.dto.flight.FlightResponseDTO;
import by.piskunou.solvdlaba.web.dto.SeatDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import by.piskunou.solvdlaba.web.mapper.FlightMapper;
import by.piskunou.solvdlaba.web.mapper.FlightRequestMapper;
import by.piskunou.solvdlaba.web.mapper.FlightResponseMapper;
import by.piskunou.solvdlaba.web.mapper.SeatMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService service;
    private final FlightMapper flightMapper;
    private final FlightRequestMapper flightRequestMapper;
    private final FlightResponseMapper flightResponseMapper;
    private final SeatMapper seatMapper;

    @GetMapping
    public List<FlightDTO> findAll(){
        return flightMapper.toDTO( service.findAll() );
    }

    @GetMapping("/{id}")
    public FlightDTO findById(@PathVariable long id) {
        return flightMapper.toDTO( service.findById(id) );
    }

    @GetMapping("/search")
    public List<FlightResponseDTO> search(@Validated FlightRequestDTO requestDTO) {
        FlightRequest flightRequest = flightRequestMapper.toEntity(requestDTO);
        return flightResponseMapper.toDTO( service.search(flightRequest));
    }

    @GetMapping("/{id}/seats")
    public List<SeatDTO> flightSeats(@PathVariable long id, @RequestParam(required = false) Boolean free) {
        return seatMapper.toDTO( service.flightSeats(id, free) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO create(@RequestBody @Validated(onCreate.class) FlightDTO dto) {
        Flight flight = flightMapper.toEntity(dto);
        return flightMapper.toDTO( service.create(flight) );
    }

    @PutMapping("/{id}")
    public FlightDTO updateById(@PathVariable long id, @RequestBody @Validated(onUpdate.class) FlightDTO dto) {
        Flight flight = flightMapper.toEntity(dto);
        return flightMapper.toDTO( service.updateById(id, flight) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }

}
