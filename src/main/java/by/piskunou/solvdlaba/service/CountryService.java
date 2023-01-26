package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findById(long id);

    Country create(Country country);

    Country updateNameById(long id, String updatedName);

    void removeById(long id);

    boolean isExists(long countryId);

    boolean isExists(String name);

}
