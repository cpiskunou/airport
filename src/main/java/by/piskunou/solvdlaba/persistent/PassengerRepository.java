package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Passenger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PassengerRepository {

    void create(Passenger passenger);

    boolean isExistsById(long id);
    
}
