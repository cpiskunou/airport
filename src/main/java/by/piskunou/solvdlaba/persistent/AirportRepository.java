package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Airport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirportRepository {

    List<Airport> findAll();

    Optional<Airport> findById(long id);

}
