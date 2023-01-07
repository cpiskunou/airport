package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import by.piskunou.solvdlaba.web.mapper.AirplaneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airplanes")
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;

    private static final String ID = "/{id}";

    @GetMapping(ID)
    public AirplaneDTO findById(@PathVariable int id) {
        Airplane airplane = airplaneService.findById(id);

        return AirplaneMapper.INSTANCE.toDTO(airplane);
    }

    @GetMapping("/{model}")
    public AirplaneDTO findByModel(@PathVariable String model) {
        Airplane airplane = airplaneService.findByModel(model);

        return AirplaneMapper.INSTANCE.toDTO(airplane);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Airplane create(@RequestBody @Validated AirplaneDTO airplaneDTO) {
        Airplane airplane = AirplaneMapper.INSTANCE.toEntity(airplaneDTO);

        airplane = airplaneService.create(airplane);

        return airplane;
    }
}
