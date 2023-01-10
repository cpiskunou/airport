package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreateException;

import java.util.List;

public interface AirplaneService {

    Airplane create(Airplane airplane);

    Airplane findById(long id);

    Airplane findByModel(String model);

    List<Airplane> findAll();

    void removeById(long id);

    void removeByModel(String model);
}
