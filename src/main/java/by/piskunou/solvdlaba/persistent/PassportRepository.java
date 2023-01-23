package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Passport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Mapper
public interface PassportRepository {

    void create(Passport passport);

}
