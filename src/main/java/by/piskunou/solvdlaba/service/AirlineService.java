package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airline;

import java.util.List;

public interface AirlineService {

    List<Airline> findAll();

    Airline findById(long id);

    List<Airline> search(Airline airline);

    Airline create(Airline airline);

    Airline updateNameById(long id, String updatedName);

    Airline updateNameByCode(String code, String updatedName);

    void removeById(long id);

    void removeByCode(String code);

    boolean isExists(long id);

    boolean isExists(String name);

    boolean isValidIata(String iata);

    boolean isValidIcao(String icao);

    boolean isValidCallsign(String callsign);

}
