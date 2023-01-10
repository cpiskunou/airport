package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.web.dto.AirlineDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    AirlineDTO toDTO(Airline entity);

    Airline toEntity(AirlineDTO dto);
}
