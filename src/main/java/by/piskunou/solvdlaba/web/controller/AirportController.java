package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.service.AirportService;
import by.piskunou.solvdlaba.web.dto.AirportDTO;
import by.piskunou.solvdlaba.web.mapper.AirportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    @GetMapping
    public List<AirportDTO> findAll() {
        return airportMapper.toDTO( airportService.findAll() );
    }

    @GetMapping("/{id}")
    public AirportDTO findById(@PathVariable long id) {
        return airportMapper.toDTO( airportService.findById(id) );
    }

}
