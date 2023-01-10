package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.web.dto.TicketDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDTO toDTO(Ticket entity);

    Ticket toEntity(TicketDTO dto);
}
