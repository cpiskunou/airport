package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.web.dto.AirlineDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    AirlineDTO toDTO(Airline entity);

    @Mapping(target = "name", defaultValue = "")
    @Mapping(target = "iata", defaultValue = "")
    @Mapping(target = "icao", defaultValue = "")
    @Mapping(target = "callsign", defaultValue = "")
    Airline toEntity(AirlineDTO dto);

    List<AirlineDTO> toDTO(List<Airline> entities);

}
