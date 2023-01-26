package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.flights.FlightRequest;
import by.piskunou.solvdlaba.web.dto.flight.FlightRequestDTO;

public interface FlightRequestMapper {

    FlightRequest toEntity(FlightRequestDTO dto);

}
