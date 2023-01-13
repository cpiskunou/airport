package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository {

    String FIND_BY_ID = """
            select id as airline_id,
                   name as airline_name
            from airline where id = ?""";

    String FIND_BY_NAME = """
            select id as airline_id,
                   name as airline_name
            from airline where name = ?""";

    String FIND_ALL = """
            select id as airline_id,
                   name as airline_name
            from airline""";

    String CREATE = "insert into airline(name) values(?)";
    String UPDATE = "update airline set name = ? where id = ?";
    String DELETE = "delete from airline where id = ?";

    List<Airline> findAll();

    Optional<Airline> findById(long id);

    Optional<Airline> findByName(String name);

    void create(Airline airline);

    void updateNameById(long id, String name);

    void removeById(long id);

}
