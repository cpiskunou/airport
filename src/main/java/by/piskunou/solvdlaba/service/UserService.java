package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    Optional<User> findById(int id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void removeById(int id);
}
