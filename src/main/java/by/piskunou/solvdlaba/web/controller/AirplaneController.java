package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotAddedException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import by.piskunou.solvdlaba.web.mapper.AirplaneMapper;
import by.piskunou.solvdlaba.web.url.AirplaneURLs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AirplaneURLs.BASE)
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;

    @GetMapping(AirplaneURLs.ID)
    public AirplaneDTO findById(@PathVariable int id) {
        return AirplaneMapper.INSTANCE.toDTO(airplaneService.findById(id));
    }

    @GetMapping("/{model}")
    public AirplaneDTO findByModel(@PathVariable String model) {
        return AirplaneMapper.INSTANCE.toDTO(airplaneService.findByModel(model));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody @Validated AirplaneDTO airplaneDTO) {
        airplaneService.save(AirplaneMapper.INSTANCE.toEntity(airplaneDTO));
    }
}
