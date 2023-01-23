package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Airline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirlineRepository {

    List<Airline> findAll();

    Optional<Airline> findById(long id);

    Optional<Airline> findByName(String name);

    void create(Airline airline);

    void updateNameById(@Param("id") long id, @Param("name") String name);

    void removeById(long id);

    boolean isExistsById(long id);

    boolean isExistsByName(String name);

}
