package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airline;

import java.util.List;

public interface AirlineService {

    List<Airline> findAll();

    Airline findById(long id);

    List<Airline> search(Airline airline);

    Airline create(Airline airline);

    Airline update(long id, Airline airline);

    void removeById(long id);

    boolean isExists(long id);

    boolean isExistsByName(long id, String name);

    boolean isExistsByIata(long id, String iata);

    boolean isExistsByIcao(long id, String icao);

    boolean isExistsByCallsign(long id, String callsign);

}
