package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.airplane.Airplane;
import by.piskunou.solvdlaba.domain.airplane.AirplaneRequest;
import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.web.dto.airplane.AirplaneDTO;
import by.piskunou.solvdlaba.web.dto.airplane.AirplaneRequestDTO;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.AirplaneMapper;
import by.piskunou.solvdlaba.web.mapper.AirplaneRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplanes")
@RequiredArgsConstructor
public class AirplaneController {

    private final AirplaneService service;
    private final AirplaneMapper airplaneMapper;
    private final AirplaneRequestMapper requestMapper;

    @GetMapping
    public List<AirplaneDTO> findAll() {
        return airplaneMapper.toDTO( service.findAll() );
    }

    @GetMapping("/{id}")
    public AirplaneDTO findById(@PathVariable int id) {
        return airplaneMapper.toDTO( service.findById(id) );
    }

    @GetMapping("/search")
    public List<AirplaneDTO> search(@Validated AirplaneRequestDTO requestDTO) {
        AirplaneRequest request = requestMapper.toEntity(requestDTO);
        return airplaneMapper.toDTO( service.search(request) );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirplaneDTO create(@RequestBody @Validated(onCreate.class) AirplaneDTO dto) {
        Airplane airplane = airplaneMapper.toEntity(dto);
        return airplaneMapper.toDTO( service.create(airplane) );
    }

    @PutMapping("/{id}")
    public AirplaneDTO updateById(@PathVariable long id, @RequestBody @Validated(onCreate.class) AirplaneDTO dto) {
        Airplane airplane = airplaneMapper.toEntity(dto);
        return airplaneMapper.toDTO( service.update(id, airplane) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable int id) {
        service.removeById(id);
    }

}
