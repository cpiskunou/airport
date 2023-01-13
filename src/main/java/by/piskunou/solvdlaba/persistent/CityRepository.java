package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {

    String FIND_BY_ID = """
             select id as city_id,
                    name as city_name
             from city where id = ?""";

    String FIND_BY_NAME = """
             select id as city_id,
                    name as city_name
             from city where name = ?""";

    String FIND_AIRPORTS_BY_ID = """
            select city.id as city_id,
                   city.name as city_name,
                   airport.id as airport_id,
                   airport.name as airport_name
            from city join airport on city.id = airport.fk_city_id 
            where city.id = ?""";

    String FIND_ALL = """
             select id as city_id,
                    name as city_name
             from city""";

    String CREATE = "insert into city(fk_country_id, name) values(?, ?)";
    String UPDATE = "update city set name = ? where id = ?";
    String DELETE = "delete from city where id = ?";

    List<City> findAll();

    Optional<City> findById(long id);

    Optional<City> findCityAirports(long id);

    Optional<City> findByName(String name);

    void create(City city, long countryId);

    void updateNameById(long id, String name);

    void removeById(long id);

}
