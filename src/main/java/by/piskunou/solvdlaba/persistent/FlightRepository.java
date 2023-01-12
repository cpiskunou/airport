package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.domain.Seat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    String FIND_BY_ID = """
            select flight.id as flight_id,
                       
                   airport_from.id as airport_from_id,
                   airport_from.name as airport_from_name,
                       
                   airport_to.id as airport_to_id,
                   airport_to.name as airport_to_name,
                       
                   airplane.id as airplane_id,
                   airplane.model as airplane_model,
                   airplane.seats_in_row as airplane_seats_in_row,
                   airplane.row_no as airplane_row_no,
                       
                   airline.id as airline_id,
                   airline.name as airline_name,
                       
                   flight.departure_time as flight_departure_time,
                   flight.arrival_time as flight_arrival_time,
                   flight.price as flight_price
            from flight inner join airport airport_from on flight.fk_airport_from_id = airport_from.id
                        inner join airport airport_to on flight.fk_airport_to_id = airport_to.id
                        inner join airline on flight.fk_airline_id = airline.id
                        inner join airplane on flight.fk_airplane_id = airplane.id
            where flight.id = ?;
            """;

    String CREATE = """
            insert into flight(fk_airport_from_id,
                               fk_airport_to_id,
                               fk_airline_id,
                               fk_airplane_id,
                               departure_time,
                               arrival_time,
                               price,
                               free_seats)
            values (?, ?, ?, ?, ?, ?, ?, ?::jsonb)
            """;

    String SEARCH = """
            select flight.id as flight_id,
                    flight.departure_time as flight_departure_time,
                    flight.arrival_time as flight_arrival_time,
                    flight.price as flight_price,
                    
                    airport_from.id as airport_from_id,
                    airport_from.name as airport_from_name,
                    
                    airport_to.id as airport_to_id,
                    airport_to.name as airport_to_name,
                    
                    airline.id as airline_id,
                    airline.name as airline_name
                       
            from flight inner join airport airport_from on flight.fk_airport_from_id = airport_from.id
                        inner join airport airport_to on flight.fk_airport_to_id = airport_to.id
                        inner join airline on flight.fk_airline_id = airline.id
            where airport_from.id = any(?) and airport_to.id = any(?) and
                  flight.departure_time between ? and ?;              
            """;

    String FREE_SEATS = """
            select jsonb_path_query_array(flight.free_seats, '$[*] ? (@.free == false)') as free_seats
            from flight where flight.id = ?;
            """;

    Optional<Flight> findById(long id);

    Optional<Long> create(Flight flight);

    List<Flight> search(Long[] fromAirports, Long[] toAirports, int passengerAmount,
                        LocalDateTime start, LocalDateTime end);

    List<Seat> freeSeats(long id);

}
