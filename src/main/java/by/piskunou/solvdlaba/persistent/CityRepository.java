package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {

    List<City> findAll();

    Optional<City> findById(long id);

    Optional<City> findCityAirports(long id);

    Optional<City> findByName(String name);

    void create(City city, long countryId);

    void updateNameById(long id, String name);

    void removeById(long id);

    boolean isExists(long id);

    boolean isExists(String name);

}
