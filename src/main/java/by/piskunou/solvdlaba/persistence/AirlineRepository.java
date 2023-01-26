package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.Airline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirlineRepository {

    List<Airline> findAll();

    Optional<Airline> findById(long id);

    List<Airline> search(Airline airline);

    void create(Airline airline);

    void updateNameById(@Param("id") long id, @Param("updatedName") String updatedName);

    void updateNameByCode(@Param("code") String code, @Param("updatedName") String updatedName);

    void removeById(long id);

    void removeByCode(String designator);

    boolean isExistsById(long id);

    boolean isExistsByName(String name);

}
