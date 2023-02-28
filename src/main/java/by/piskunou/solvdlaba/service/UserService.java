package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(long id, boolean withTickets);

    User findByUsername(String username);

    List<User> search(User user);

    User create(User user);

    User updateById(long id, User user);

    void removeById(int id);

    boolean isExists(long id);

    boolean isExistsByUsername(Long id, String username);

    boolean isExistsByEmail(Long id, String email);

}
