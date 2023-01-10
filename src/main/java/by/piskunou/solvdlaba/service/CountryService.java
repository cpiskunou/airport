package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findCountryCities(long id);

    Country findCountryAirports(long id);

    Country findById(long id);

    Country findByName(String name);

    Country create(Country country);

    Country updateNameById(long id, String name);

    void removeById(long id);

    void removeByName(String name);
}
