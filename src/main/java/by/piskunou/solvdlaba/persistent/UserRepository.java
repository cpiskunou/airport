package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void register(User user);

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    Optional<User> findUserTickets(long id);

    void removeById(long id);

    void updateUsernameById(long id, String username);

    boolean isExists(String username);

    boolean isExists(long id);

}
