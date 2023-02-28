package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByIdWithTickets(long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> search(User user);

    void create(User user);

    void update(User user);

    void updatePasswordByUsername(@Param("password") String password, @Param("username") String username);

    void removeById(long id);

    boolean isExistsById(long id);

    boolean isExistsByUsername(@Param("id") Long id, @Param("username") String username);

    boolean isExistsByEmail(@Param("id") Long id, @Param("email") String email);


}
