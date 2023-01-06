package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotAddedException;

import java.util.Optional;

public interface AirplaneService {
    public void save(Airplane airplane) throws ResourceNotAddedException;
    public Airplane findById(long id);
    public Airplane findByModel(String model);
    public void removeById(long id);
    public void removeByModel(String model);
}
