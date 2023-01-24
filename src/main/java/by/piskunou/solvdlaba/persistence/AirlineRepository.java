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

    Optional<Airline> findByDesignator(String designator);

    void create(Airline airline);

    void updateNameById(@Param("id") long id, @Param("name") String name);

    void updateNameByDesignator(@Param("designator") String designator, @Param("name") String name);

    void removeById(long id);

    void removeByDesignator(String designator);

    boolean isExistsById(long id);

    boolean isExistsByName(String name);

}
