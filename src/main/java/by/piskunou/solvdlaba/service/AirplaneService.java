package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.airplane.Airplane;
import by.piskunou.solvdlaba.domain.airplane.AirplaneRequest;

import java.util.List;

public interface AirplaneService {

    List<Airplane> findAll();

    Airplane findById(long id);

    List<Airplane> search(AirplaneRequest request);

    Airplane create(Airplane airplane);

    Airplane updateModelById(long id, String updatedModel);

    void removeById(long id);

    boolean isExists(String name);

    boolean isExists(long id);

}
