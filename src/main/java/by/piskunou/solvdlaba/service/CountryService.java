package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findCountryCities(long countryId);

    Country findCountryAirports(long countryId);

    Country findById(long id);

    Country findByName(String name);

    Country create(Country country);

    Country updateNameById(long id, String name);

    void removeById(long id);

    boolean isExists(long countryId);

    boolean isExists(String name);

}
