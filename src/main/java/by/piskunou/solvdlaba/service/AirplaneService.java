package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreateException;

public interface AirplaneService {
    Airplane create(Airplane airplane) throws ResourceNotCreateException;
    Airplane findById(long id);
    Airplane findByModel(String model);
    void removeById(long id);
    void removeByModel(String model);
}
