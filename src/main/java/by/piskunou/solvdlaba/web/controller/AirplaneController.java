package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.AirplaneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplanes")
@RequiredArgsConstructor
public class AirplaneController {

    private final AirplaneService airplaneService;
    private final AirplaneMapper airplaneMapper;

    @GetMapping
    public List<AirplaneDTO> findAll() {
        return airplaneMapper.toDTO(airplaneService.findAll());
    }

    @GetMapping("/{id}")
    public AirplaneDTO findById(@PathVariable int id) {
        Airplane airplane = airplaneService.findById(id);
        return airplaneMapper.toDTO(airplane);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirplaneDTO create(@RequestBody @Validated(onCreate.class) AirplaneDTO airplaneDTO) {
        Airplane airplane = airplaneMapper.toEntity(airplaneDTO);

        airplane = airplaneService.create(airplane);
        return airplaneMapper.toDTO(airplane);
    }

    //todo: write put method

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable int id) {
        airplaneService.removeById(id);
    }

}
