package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.service.CityService;
import by.piskunou.solvdlaba.web.dto.CityDTO;
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

    private final CityService cityService;

    private final CityMapper cityMapper;

    private static final String ID = "/{id}";

    private static final String NAME = "/{name}";

    @GetMapping
    public List<CityDTO> findAll() {
        return cityService.findAll()
                          .stream()
                          .map(cityMapper::toDTO)
                          .toList();
    }

    @GetMapping (ID + "/airports")
    public CityDTO findCountryCities(@PathVariable long id) {
        City city = cityService.findCityAirports(id);

        return cityMapper.toDTO(city);
    }

    @GetMapping(ID)
    public CityDTO findById(@PathVariable long id) {
        City city = cityService.findById(id);

        return cityMapper.toDTO(city);
    }

//    @GetMapping (NAME)
//    public CityDTO findByName(@PathVariable String name) {
//        City city = cityService.findByName(name);
//
//        return cityMapper.toDTO(city);
//    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public CityDTO create(@RequestBody @Validated CityDTO cityDTO, @RequestParam("country_id") long countryId) {
        City city = cityMapper.toEntity(cityDTO);

        city = cityService.create(city, countryId);

        return cityMapper.toDTO(city);
    }

    @PatchMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public CityDTO updateNameById(@PathVariable long id, @RequestParam String name) {
        City city = cityService.updateNameById(id, name);

        return cityMapper.toDTO(city);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public void removeById(@PathVariable long id) {
        cityService.removeById(id);
    }

//    @DeleteMapping(NAME)
//    @ResponseStatus(HttpStatus.OK)
//    public void removeByName(@PathVariable String name) {
//        cityService.removeByName(name);
//    }
}
