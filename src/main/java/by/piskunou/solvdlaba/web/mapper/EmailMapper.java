package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Email;
import by.piskunou.solvdlaba.web.dto.EmailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailDTO toDTO(Email entity);

    Email toEntity(EmailDTO dto);

}
