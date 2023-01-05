package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.repository.dao.AirplaneDAO;
import by.piskunou.solvdlaba.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    private final AirplaneDAO airplaneDAO;

    @Autowired
    public AirplaneServiceImpl(AirplaneDAO airplaneDAO) {
        this.airplaneDAO = airplaneDAO;
    }

    @Override
    public void save(Airplane airplane) {
        airplaneDAO.save(airplane);
    }

    @Override
    public Optional<Airplane> findById(int id) {
        return airplaneDAO.findById(id);
    }

    @Override
    public Optional<Airplane> findByModel(String model) {
        return airplaneDAO.findByModel(model);
    }

    @Override
    public void removeById(int id) {
        airplaneDAO.removeById(id);
    }

    @Override
    public void removeByModel(String model) {
        airplaneDAO.removeByModel(model);
    }
}
