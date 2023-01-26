package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.web.dto.AirportDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportDTO toDTO(Airport entity);

    @Mapping(target = "name", defaultValue = "")
    @Mapping(target = "iata", defaultValue = "")
    @Mapping(target = "icao", defaultValue = "")
    Airport toEntity(AirportDTO dto);

    List<AirportDTO> toDTO(List<Airport> entities);

}
