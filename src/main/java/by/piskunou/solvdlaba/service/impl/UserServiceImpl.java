package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.repository.dao.UserDAO;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public void save(User user) {
        userDAO.save(user);
    }
    @Override
    public Optional<User> findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
    @Override
    public void removeById(int id) {
        userDAO.removeById(id);
    }
}
