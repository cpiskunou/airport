package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.UserNotRegisteredException;
import by.piskunou.solvdlaba.repository.dao.UserDAO;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    @Transactional
    public void save(User user) {
        if(findByUsernameAndByIdNot(user.getId(), user.getUsername()) != null) {
            throw new UserNotRegisteredException("Username is taken");
        }
        userDAO.save(user);
    }
    @Override
    public User findById(long id) {
        Optional<User> user = userDAO.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("This's no user with such id");
        }

        return user.get();
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userDAO.findByUsername(username);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("This's no user with such id");
        }

        return user.get();
    }

    @Override
    public User findByUsernameAndByIdNot(long id, String username) {
        Optional<User> user = userDAO.findByUsernameAndByIdNot(id, username);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("This's no user with such id");
        }

        return user.get();
    }
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
    @Override
    @Transactional
    public void removeById(int id) {
        userDAO.removeById(id);
    }
}
