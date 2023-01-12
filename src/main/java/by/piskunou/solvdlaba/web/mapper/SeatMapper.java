package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Seat;
import by.piskunou.solvdlaba.web.dto.SeatDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    SeatDTO toDTO(Seat entity);

    Seat toEntity(SeatDTO dto);

}
