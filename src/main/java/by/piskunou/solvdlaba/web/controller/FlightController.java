package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.service.FlightService;
import by.piskunou.solvdlaba.web.dto.FlightDTO;
import by.piskunou.solvdlaba.web.dto.FlightRequest;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.FlightMapper;
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

    private final FlightService flightService;

    private final FlightMapper flightMapper;

    private static final String ID = "/{id}";

    @GetMapping(ID)
    public FlightDTO findById(@PathVariable long id) {
        Flight flight = flightService.findById(id);

        return flightMapper.toDTO(flight);
    }

    @GetMapping("/search")
    public List<FlightDTO> search(FlightRequest flightRequest) {
        List<Flight> flights = flightService.search(flightRequest);

        return flights.stream()
                      .map(flightMapper::toDTO)
                      .toList();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @Validated(onCreate.class)
    public FlightDTO create(@RequestBody @Valid FlightDTO flightDTO) {
        Flight flight = flightMapper.toEntity(flightDTO);

        flight = flightService.create(flight);

        return flightMapper.toDTO(flight);
    }
}
