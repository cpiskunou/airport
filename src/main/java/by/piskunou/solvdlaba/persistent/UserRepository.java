package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<Long> register(User user);

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndByIdNot(long id, String username);

    List<User> findAll();

    Optional<User> findUserTickets(long id);

    void removeById(long id);

    Optional<User> updateUsernameById(long id, String username);

}
