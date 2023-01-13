package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Passenger;

public interface PassengerRepository {

    void create(Passenger passenger);

    boolean isExists(long id);
    
}
