package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airline;

import java.util.List;

public interface AirlineService {
    List<Airline> findAll();

    Airline findById(long id);

    Airline create(Airline airline);

    Airline updateNameById(long id, String name);

    void removeById(long id);
}
