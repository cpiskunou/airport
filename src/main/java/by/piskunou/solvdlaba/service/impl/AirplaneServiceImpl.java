package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotAddedException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.repository.dao.AirplaneDAO;
import by.piskunou.solvdlaba.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneDAO airplaneDAO;

    @Override
    @Transactional
    public void save(Airplane airplane) {
        if(findByModel(airplane.getModel()) != null) {
            throw new ResourceNotAddedException("Such model of airplane already exists");
        }
        airplaneDAO.save(airplane);
    }

    @Override
    public Airplane findById(long id) {
        Optional<Airplane> airplane = airplaneDAO.findById(id);
        if(airplane.isEmpty()) {
            throw new ResourceNotFoundException("This's no  with such id");
        }
        return airplane.get();
    }

    @Override
    public Airplane findByModel(String model) {
        Optional<Airplane> airplane = airplaneDAO.findByModel(model);
        if(airplane.isEmpty()) {
            throw new ResourceNotFoundException("This's no such model of airplane");
        }
        return airplane.get();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        airplaneDAO.removeById(id);
    }

    @Override
    @Transactional
    public void removeByModel(String model) {
        airplaneDAO.removeByModel(model);
    }
}
