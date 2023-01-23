package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.service.CountryService;
import by.piskunou.solvdlaba.web.dto.CountryDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final CountryMapper mapper;

    @GetMapping
    public List<CountryDTO> findAll() {
        return mapper.toDTOList(countryService.findAll());
    }

    @GetMapping ("/{id}/cities")
    public CountryDTO findCountryCities(@PathVariable long id) {
        Country country = countryService.findCountryCities(id);
        return mapper.toDTO(country);
    }

    @GetMapping ("/{id}/airports")
    public CountryDTO findCountryAirports(@PathVariable long id) {
        Country country = countryService.findCountryAirports(id);
        return mapper.toDTO(country);
    }

    @GetMapping("/{id}")
    public CountryDTO findById(@PathVariable long id) {
        Country country = countryService.findById(id);
        return mapper.toDTO(country);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDTO create(@RequestBody @Validated(onCreate.class) CountryDTO countryDTO) {
        Country country = mapper.toEntity(countryDTO);

        country = countryService.create(country);
        return mapper.toDTO(country);
    }

    @PutMapping("/{id}")
    public CountryDTO updateNameById(@PathVariable long id, @RequestParam String name) {
        Country country = countryService.updateNameById(id, name);
        return mapper.toDTO(country);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable long id) {
        countryService.removeById(id);
    }

}
