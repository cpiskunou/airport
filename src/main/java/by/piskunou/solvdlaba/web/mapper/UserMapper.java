package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);

}
