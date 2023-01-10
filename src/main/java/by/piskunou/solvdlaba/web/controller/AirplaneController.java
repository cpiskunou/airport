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

    private final AirplaneMapper mapper;

    private static final String ID = "/{id}";

    private static final String MODEL = "/{model}";

    @GetMapping(ID)
    public AirplaneDTO findById(@PathVariable int id) {
        Airplane airplane = airplaneService.findById(id);

        return mapper.toDTO(airplane);
    }

//    @GetMapping(MODEL)
//    public AirplaneDTO findByModel(@PathVariable String model) {
//        Airplane airplane = airplaneService.findByModel(model);
//
//        return mapper.toDTO(airplane);
//    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public AirplaneDTO create(@RequestBody @Validated AirplaneDTO airplaneDTO) {
        Airplane airplane = mapper.toEntity(airplaneDTO);

        airplane = airplaneService.create(airplane);

        return mapper.toDTO(airplane);
    }

    @DeleteMapping(ID)
    @ResponseStatus(HttpStatus.OK)
    public void removeById(@PathVariable int id) {
        airplaneService.removeById(id);
    }
    
//    @DeleteMapping(MODEL)
//    @ResponseStatus(HttpStatus.OK)
//    public void removeByModel(@PathVariable String model) {
//        airplaneService.removeByModel(model);
//    }
}
