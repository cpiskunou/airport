package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.airplane.Airplane;
import by.piskunou.solvdlaba.domain.airplane.AirplaneRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirplaneRepository {

    List<Airplane> findAll();

    Optional<Airplane> findById(long id);

    List<Airplane> search(AirplaneRequest request);

    void create(Airplane airplane);

    void updatedModelById(@Param("id") long id, @Param("updatedModel") String updatedModel);

    void removeById(long id);

    boolean isExistsByModel(String model);

    boolean isExistsById(long id);

}
