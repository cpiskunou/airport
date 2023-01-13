package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    String FIND_BY_ID = """
        select id as user_id,
               username as user_name
        from \"user\" where id = ?""";

    String FIND_BY_USERNAME = """
            select id as user_id,
                   username as user_name
            from \"user\" where username = ?""";

    //TODO: write whole join
    String FIND_TICKETS_BY_ID = "";

    String FIND_ALL = """
            "select id as user_id,
                    username as user_name
            from \"user\"""";

    String REGISTER = "insert into \"user\" (username) values(?)";
    String UPDATE = "update \"user\" set username = ? where id = ?";
    String DELETE = "delete from \"user\" where id = ?";

    void register(User user);

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    Optional<User> findUserTickets(long id);

    void removeById(long id);

    void updateUsernameById(long id, String username);

}
