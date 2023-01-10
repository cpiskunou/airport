package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User findById(long id);

    User findUserTickets(long id);

    User findByUsername(String username);

    User findByUsernameAndNotById(long id, String username);

    List<User> findAll();

    User updateUsernameById(long id, String username);

    void removeById(int id);
}
