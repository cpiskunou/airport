package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.service.AirlineService;
import by.piskunou.solvdlaba.web.dto.AirlineDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.AirlineMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
@Validated
public class AirlineController {

    private final AirlineService airlineService;
    private final AirlineMapper airlineMapper;

    @GetMapping
    public List<AirlineDTO> findAll() {
        return airlineMapper.toDTO(airlineService.findAll());
    }

    @GetMapping("/{id}")
    public AirlineDTO findById(@PathVariable long id) {
        Airline airline = airlineService.findById(id);

        return airlineMapper.toDTO(airline);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(onCreate.class)
    public AirlineDTO create(@RequestBody @Valid AirlineDTO airlineDTO) {
        Airline airline = airlineMapper.toEntity(airlineDTO);

        airline = airlineService.create(airline);

        return airlineMapper.toDTO(airline);
    }

    @PutMapping ("/{id}")
    public AirlineDTO updateNameById(@PathVariable long id, @RequestParam String name) {
        Airline airline = airlineService.updateNameById(id, name);

        return airlineMapper.toDTO(airline);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        airlineService.removeById(id);
    }

}
