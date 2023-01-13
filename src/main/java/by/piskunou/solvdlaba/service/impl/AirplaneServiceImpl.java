package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistent.AirplaneRepository;
import by.piskunou.solvdlaba.persistent.impl.AirplaneRepositoryImpl;
import by.piskunou.solvdlaba.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;

    @Override
    @Transactional
    public Airplane create(Airplane airplane) {
        if(isExists(airplane.getModel())) {
            throw new ResourceAlreadyExistsException("Such model of airplane already exists");
        }
        airplaneRepository.create(airplane);
        return airplane;
    }

    @Override
    @Transactional(readOnly = true)
    public Airplane findById(long id) {
        return airplaneRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotExistsException("There's no airplane with such id"));
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
    public boolean isExists(String model) {
        return airplaneRepository.isExists(model);
    }

}
