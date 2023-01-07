package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreateException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.repository.AirplaneRepository;
import by.piskunou.solvdlaba.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneRepository airplaneRepository;

    @Override
    @Transactional
    public Airplane create(Airplane airplane) throws ResourceNotCreateException {
        if(findByModel(airplane.getModel()) != null) {
            throw new ResourceNotCreateException("Such model of airplane already exists");
        }
        airplane = airplaneRepository.save(airplane);

        return airplane;
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
