package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourseAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.persistent.impl.AirplaneRepositoryImpl;
import by.piskunou.solvdlaba.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepositoryImpl airplaneRepository;

    @Override
    @Transactional
    public Airplane create(Airplane airplane) {
        if(isExists(airplane.getModel())) {
            throw new ResourseAlreadyExistsException("Such model of airplane already exists");
        }

        airplaneRepository.create(airplane);

        return airplane;
    }

    @Override
    public boolean isExists(String model) {
        return airplaneRepository.findByModel(model)
                                 .isPresent();
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
    public List<Airplane> findAll() {
        return airplaneRepository.findAll();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        airplaneRepository.removeById(id);
    }

}
