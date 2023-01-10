package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreateException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.repository.AirplaneRepository;
import by.piskunou.solvdlaba.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;

    @Override
    @Transactional
    public Airplane create(Airplane airplane) {
        Optional<Airplane> airplaneOptional = airplaneRepository.findByModel(airplane.getModel());
        if(airplaneOptional.isPresent()) {
            throw new ResourceNotCreateException("Such model of airplane already exists");
        }

        airplaneOptional = airplaneRepository.create(airplane);

        return airplaneOptional.orElseThrow(() -> new ResourceNotCreateException("Airplane wasn't created"));
    }

    @Override
    @Transactional(readOnly = true)
    public Airplane findById(long id) {
        Optional<Airplane> airplane = airplaneRepository.findById(id);
        if(airplane.isEmpty()) {
            throw new ResourceNotFoundException("There's no airplane with such id");
        }

        return airplane.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Airplane findByModel(String model) {
        Optional<Airplane> airplane = airplaneRepository.findByModel(model);
        if(airplane.isEmpty()) {
            throw new ResourceNotFoundException("There's no such model of airplane");
        }

        return airplane.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Airplane> findAll() {
        return airplaneRepository.findAll();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        airplaneRepository.removeById(id);
    }

    @Override
    @Transactional
    public void removeByModel(String model) {
        airplaneRepository.removeByModel(model);
    }
}
