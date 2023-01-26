package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airport;

import java.util.List;

public interface AirportService {

    List<Airport> findAll();

    Airport findById(long id);

    List<Airport> search(Airport airport);

    Airport create(long cityId, Airport airport);

    Airport updateNameById(long id, String updatedName);

    Airport updateNameByCode(String code, String updatedName);

    void removeById(long id);

    void removeByCode(String code);

    boolean isExists(long id);

    boolean isExists(String name);

    boolean isValidIata(String iata);

    boolean isValidIcao(String icao);

}
