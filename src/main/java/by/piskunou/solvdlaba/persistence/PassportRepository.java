package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.Passport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PassportRepository {

    void create(@Param("passport") Passport passport);

}
