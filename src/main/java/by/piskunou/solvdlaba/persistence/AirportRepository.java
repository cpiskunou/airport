package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.Airport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirportRepository {

    List<Airport> findAll();

    Optional<Airport> findById(long id);

    List<Airport> search(Airport airport);

    void create(@Param("cityId") long cityId, @Param("airport") Airport airport);

    void updateNameById(@Param("id") long id, @Param("updatedName") String updatedName);

    void updateNameByCode(@Param("code") String code, @Param("updatedName") String updatedName);

    void removeById(long id);

    void removeByCode(String code);

    boolean isExistsById(long id);

    boolean isExistsByName(String name);

}
