package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.FlightRequest;
import by.piskunou.solvdlaba.web.dto.FlightRequestDTO;

public interface FlightRequestMapper {

    FlightRequest toEntity(FlightRequestDTO dto);

}
