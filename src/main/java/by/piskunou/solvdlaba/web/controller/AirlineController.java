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
    public List<AirlineDTO> search(@Validated(onSearch.class) AirlineDTO airlineDTO) {
        Airline airline = mapper.toEntity(airlineDTO);
        return mapper.toDTO( service.search(airline) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirlineDTO create(@RequestBody @Validated(onCreate.class) AirlineDTO airlineDTO) {
        Airline airline = mapper.toEntity(airlineDTO);

        airline = service.create(airline);
        return mapper.toDTO(airline);
    }

    @PutMapping ("/{id}")
    public AirlineDTO updateNameById(@PathVariable long id,
                                     @RequestParam("updated-name") String updatedName) {
        return mapper.toDTO( service.updateNameById(id, updatedName) );
    }

    @PutMapping("/update")
    public AirlineDTO updateNameByCode(@RequestParam String code,
                                       @RequestParam("updated-name") String updatedName) {
        return mapper.toDTO( service.updateNameByCode(code, updatedName) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeByCode(@RequestParam String code) {
        service.removeByCode(code);
    }

}
