package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.FlightResponse;
import by.piskunou.solvdlaba.web.dto.FlightResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightResponseMapper {

    FlightResponseDTO toDTO(FlightResponse entity);

    FlightResponse toEntity(FlightResponseDTO dto);

}
