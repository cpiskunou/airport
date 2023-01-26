package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.service.AirportService;
import by.piskunou.solvdlaba.web.dto.AirportDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onSearch;
import by.piskunou.solvdlaba.web.mapper.AirportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService service;
    private final AirportMapper mapper;

    @GetMapping
    public List<AirportDTO> findAll() {
        return mapper.toDTO( service.findAll() );
    }

    @GetMapping("/{id}")
    public AirportDTO findById(@PathVariable long id) {
        return mapper.toDTO( service.findById(id) );
    }

    @GetMapping("/search")
    public List<AirportDTO> search(@Validated(onSearch.class) AirportDTO airportDTO) {
        Airport airport = mapper.toEntity(airportDTO);
        return mapper.toDTO( service.search(airport) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirportDTO create(@RequestParam("city-id") long cityId,
                             @RequestBody @Validated(onCreate.class) AirportDTO airportDTO) {
        Airport airport = mapper.toEntity(airportDTO);
        return mapper.toDTO( service.create(cityId, airport) );
    }

    @PutMapping("{id}")
    public AirportDTO updateNameById(@PathVariable long id,
                                     @RequestParam("updated-name") String updatedName) {
        return mapper.toDTO( service.updateNameById(id, updatedName) );
    }

    @PutMapping("/update")
    public AirportDTO updateNameByCode(@RequestParam String code,
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
