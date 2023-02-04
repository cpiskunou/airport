package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.service.AirlineService;
import by.piskunou.solvdlaba.web.dto.AirlineDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onSearch;
import by.piskunou.solvdlaba.web.mapper.AirlineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService service;
    private final AirlineMapper mapper;

    @GetMapping
    public List<AirlineDTO> findAll() {
        return mapper.toDTO( service.findAll() );
    }

    @GetMapping("/{id}")
    public AirlineDTO findById(@PathVariable long id) {
        return mapper.toDTO( service.findById(id) );
    }

    @GetMapping("/search")
    public List<AirlineDTO> search(@Validated(onSearch.class) AirlineDTO dto) {
        Airline airline = mapper.toEntity(dto);
        return mapper.toDTO( service.search(airline) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirlineDTO create(@RequestBody @Validated(onCreate.class) AirlineDTO dto) {
        Airline airline = mapper.toEntity(dto);
        return mapper.toDTO( service.create(airline) );
    }

    @PutMapping("/{id}")
    public AirlineDTO updateById(@PathVariable long id, @RequestBody @Validated(onCreate.class) AirlineDTO dto) {
        Airline airline = mapper.toEntity(dto);
        return mapper.toDTO( service.update(id, airline) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }

}
