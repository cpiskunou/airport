package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airplane;

import java.util.List;

public interface AirplaneService {

    Airplane create(Airplane airplane);

    Airplane findById(long id);

    List<Airplane> findAll();

    void removeById(long id);

    boolean isExists(String name);

    boolean isExists(long id);

}
