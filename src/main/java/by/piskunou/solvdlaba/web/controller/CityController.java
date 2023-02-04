package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.service.CityService;
import by.piskunou.solvdlaba.web.dto.CityDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService service;
    private final CityMapper mapper;

    @GetMapping
    public List<CityDTO> findAll() {
        return mapper.toDTO( service.findAll() );
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable long id, @RequestParam(required = false) boolean withAirports) {
        return mapper.toDTO( service.findById(id, withAirports) );
    }

    @GetMapping("/search")
    public List<CityDTO> search(@RequestParam String inquiry) {
        return mapper.toDTO( service.search(inquiry) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO create(@RequestParam("country-id") long countryId,
                          @RequestBody @Validated CityDTO dto) {
        City city = mapper.toEntity(dto);
        return mapper.toDTO( service.create(countryId, city) );
    }

    @PutMapping("/{id}")
    public CityDTO updateById(@PathVariable long id, @RequestParam("country-id") long countryId,
                              @RequestBody @Validated CityDTO dto) {
        City city = mapper.toEntity(dto);
        return mapper.toDTO( service.update(id, countryId, city) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }

}
