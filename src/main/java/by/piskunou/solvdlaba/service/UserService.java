package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User findById(long id);
    User findByUsername(String username);
    User findByUsernameAndByIdNot(long id, String username);
    List<User> findAll();
    void removeById(int id);
}
