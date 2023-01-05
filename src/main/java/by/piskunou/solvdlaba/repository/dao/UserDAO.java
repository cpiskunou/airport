package by.piskunou.solvdlaba.repository.dao;

import by.piskunou.solvdlaba.domain.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {
    public void save(User user) {}

    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    public List<User> findAll() {
        return Collections.emptyList();
    }

    public void removeById(int id) {}
}
