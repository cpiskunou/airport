package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "entity", target = ".")
    UserDTO toDTO(User entity);

    @Mapping(source = "dto", target = ".")
    User toEntity(UserDTO dto);
}
