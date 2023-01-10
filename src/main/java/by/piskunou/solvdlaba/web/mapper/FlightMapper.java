package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.web.dto.FlightDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDTO toDTO(Flight entity);

    Flight toEntity(FlightDTO dto);
}
