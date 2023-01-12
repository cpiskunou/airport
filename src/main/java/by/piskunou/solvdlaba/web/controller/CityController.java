package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.service.CityService;
import by.piskunou.solvdlaba.web.dto.CityDTO;
import by.piskunou.solvdlaba.web.mapper.CityMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final CityMapper cityMapper;

    @GetMapping
    public List<CityDTO> findAll() {
        return cityService.findAll()
                          .stream()
                          .map(cityMapper::toDTO)
                          .toList();
    }

    @GetMapping ("/{id}/airports")
    public CityDTO findCountryCities(@PathVariable long id) {
        City city = cityService.findCityAirports(id);

        return cityMapper.toDTO(city);
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable long id) {
        City city = cityService.findById(id);

        return cityMapper.toDTO(city);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO create(@RequestBody @Valid CityDTO cityDTO, @RequestParam("country_id") long countryId) {
        City city = cityMapper.toEntity(cityDTO);

        city = cityService.create(city, countryId);

        return cityMapper.toDTO(city);
    }

    @PutMapping("/{id}")
    public CityDTO updateNameById(@PathVariable long id, @RequestParam String name) {
        City city = cityService.updateNameById(id, name);

        return cityMapper.toDTO(city);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        cityService.removeById(id);
    }

}
