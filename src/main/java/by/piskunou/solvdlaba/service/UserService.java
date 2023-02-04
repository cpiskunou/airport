package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;

public interface UserService {

    User findById(long id, boolean withTickets);

    List<User> findAll();

    List<User> search(User user);

    User create(User user);

    User updateById(long id, User user);

    void removeById(int id);

    boolean isExists(long id);

    boolean isExists(long id, String username);

}
