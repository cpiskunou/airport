package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City findCityAirports(long id);

    City findById(long id);

    City findByName(String name);

    City create(City city, long countryId);

    City updateNameById(long id, String name);

    void removeById(long id);

    boolean isExists(long id);

    boolean isExists(String name);

}
