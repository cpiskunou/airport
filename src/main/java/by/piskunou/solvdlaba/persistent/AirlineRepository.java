package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository {

    List<Airline> findAll();

    Optional<Airline> findById(long id);

    Optional<Airline> findByName(String name);

    void create(Airline airline);

    void updateNameById(long id, String name);

    void removeById(long id);

}
