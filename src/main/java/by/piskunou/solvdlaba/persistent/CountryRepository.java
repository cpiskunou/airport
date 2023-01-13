package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {

    String FIND_BY_ID = """
            select id as country_id,
                   name as country_name
            from country where id = ?""";

    String FIND_BY_NAME = """
            select id as country_id,
                   name as country_name
            from country where name = ?""";

    String FIND_CITIES_BY_ID = """
        select country.id as country_id,
               country.name as country_name,
               city.id as city_id,
               city.name as city_name
        from country join city on country.id = city.fk_country_id 
        where country.id = ?""";

    String FIND_AIRPORTS_BY_ID = """
        select country.id as country_id,
               country.name as country_name,
               city.id as city_id,
               city.name as city_name,
               airport.id as airport_id,
               airport.name as airport_name
        from country join city on country.id = city.fk_country_id
                     join airport on city.id = airport.fk_city_id
        where country.id = ?""";

    String FIND_ALL = """
            select id as country_id,
                   name as country_name
            from country""";

    String CREATE = "insert into country(name) values(?)";
    String UPDATE = "update country set name = ? where id = ?";
    String DELETE = "delete from country where id = ?";

    List<Country> findAll();

    Optional<Country> findById(long id);

    Optional<Country> findByName(String name);

    Optional<Country> findCountryCities(long id);

    Optional<Country> findCountryAirports(long id);

    void create(Country country);

    void updateNameById(long id, String name);

    void removeById(long id);

}
