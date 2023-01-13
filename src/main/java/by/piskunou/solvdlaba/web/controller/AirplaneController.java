package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.AirplaneMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airplanes")
@RequiredArgsConstructor
@Validated
public class AirplaneController {

    private final AirplaneService airplaneService;
    private final AirplaneMapper mapper;

    @GetMapping("/{id}")
    public AirplaneDTO findById(@PathVariable int id) {
        Airplane airplane = airplaneService.findById(id);

        return mapper.toDTO(airplane);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(onCreate.class)
    public AirplaneDTO create(@RequestBody @Valid AirplaneDTO airplaneDTO) {
        Airplane airplane = mapper.toEntity(airplaneDTO);

        airplane = airplaneService.create(airplane);

        return mapper.toDTO(airplane);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable int id) {
        airplaneService.removeById(id);
    }

}
