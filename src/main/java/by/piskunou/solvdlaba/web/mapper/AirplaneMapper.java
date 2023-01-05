package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.Airplane;
import by.piskunou.solvdlaba.web.dto.AirplaneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirplaneMapper {
    AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);

    @Mapping(source = "entity", target = ".")
    AirplaneDTO toDTO(Airplane entity);

    @Mapping(source = "dto", target = ".")
    Airplane toEntity(AirplaneDTO dto);
}
