package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Airplane;

import java.util.List;
import java.util.Optional;

public interface AirplaneRepository {

    void create(Airplane airplane);

    Optional<Airplane> findById(long id);

    List<Airplane> findAll();

    void removeById(long id);

    boolean isExists(String model);

}
