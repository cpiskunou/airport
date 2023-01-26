package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.flights.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface FlightRepository {

    Optional<Flight> findById(long id);

    List<Flight> search(@Param("fromAirports") List<Long> fromAirports, @Param("toAirports") List<Long> toAirports,
                        @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    Optional<Flight> freeSeats(long id);

    void create(Flight flight);

    void bookSeat(String number);

    boolean isExistsById(long id);

}
