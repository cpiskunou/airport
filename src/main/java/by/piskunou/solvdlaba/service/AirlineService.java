package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airline;

import java.util.List;

public interface AirlineService {

    List<Airline> findAll();

    Airline findById(long id);

    Airline findByDesignator(String designator);

    Airline create(Airline airline);

    Airline updateNameById(long id, String name);

    Airline updateNameByDesignator(String designator, String name);

    void removeById(long id);

    void removeByDesignator(String designator);

    boolean isExists(long id);

    boolean isExists(String name);

    boolean isValidIata(String iata);

    boolean isValidIcao(String icao);

    boolean isValidCallsign(String callsign);

}
