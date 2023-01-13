package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {

    List<Country> findAll();

    Optional<Country> findById(long id);

    Optional<Country> findByName(String name);

    Optional<Country> findCountryCities(long id);

    Optional<Country> findCountryAirports(long id);

    void create(Country country);

    void updateNameById(long id, String name);

    void removeById(long id);

}
