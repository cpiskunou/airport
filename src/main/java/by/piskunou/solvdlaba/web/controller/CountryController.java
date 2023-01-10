package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.service.CountryService;
import by.piskunou.solvdlaba.web.dto.CountryDTO;
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

    private static final String ID = "/{id}";

    private static final String NAME = "/{name}";

    @GetMapping
    public List<CountryDTO> findAll() {
        return countryService.findAll()
                            .stream()
                            .map(mapper::toDTO)
                            .toList();
    }

    @GetMapping (ID + "/cities")
    public CountryDTO findCountryCities(@PathVariable long id) {
        Country country = countryService.findCountryCities(id);

        return mapper.toDTO(country);
    }

    @GetMapping (ID + "/airports")
    public CountryDTO findCountryAiports(@PathVariable long id) {
        Country country = countryService.findCountryAirports(id);

        return mapper.toDTO(country);
    }

    @GetMapping(ID)
    public CountryDTO findById(@PathVariable long id) {
        Country country = countryService.findById(id);

        return mapper.toDTO(country);
    }

//    @GetMapping (NAME)
//    public CountryDTO findByName(@PathVariable String name) {
//        Country country = countryService.findByName(name);
//
//        return mapper.toDTO(country);
//    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public CountryDTO create(@RequestBody @Validated CountryDTO countryDTO) {
        Country country = mapper.toEntity(countryDTO);

        country = countryService.create(country);

        return mapper.toDTO(country);
    }

    @PatchMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public CountryDTO updateNameById(@PathVariable long id, @RequestParam String name) {
        Country country = countryService.updateNameById(id, name);

        return mapper.toDTO(country);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public void removeById(@PathVariable long id) {
        countryService.removeById(id);
    }

//    @DeleteMapping(NAME)
//    @ResponseStatus(HttpStatus.OK)
//    public void removeByName(@PathVariable String name) {
//        countryService.removeByName(name);
//    }
}
