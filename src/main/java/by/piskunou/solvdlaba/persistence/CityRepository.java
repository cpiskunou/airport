package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CityRepository {

    List<City> findAll();

    Optional<City> findById(long id);

    Optional<City> findCityAirports(long id);

    Optional<City> findByName(String name);

    void create(@Param("city") City city, @Param("countryId") long countryId);

    void updateNameById(@Param("id") long id, @Param("name") String name);

    void removeById(long id);

    boolean isExistsById(long id);

    boolean isExistsByName(String name);

}
