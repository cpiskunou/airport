package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Airplane;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirplaneRepository {

    Optional<Airplane> findById(long id);

    List<Airplane> findAll();

    void create(Airplane airplane);

    void removeById(long id);

    boolean isExistsByModel(String model);

    boolean isExistsById(long id);

}
