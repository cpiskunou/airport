package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourseNotExistsException;
import by.piskunou.solvdlaba.domain.exception.UserNotRegisteredException;
import by.piskunou.solvdlaba.persistent.impl.UserRepositoryImpl;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;

    @Override
    @Transactional
    public User register(User user) {
        if (isExists(user.getUsername())) {
            throw new UserNotRegisteredException("Username is taken");
        }

        userRepository.register(user);

        return user;
    }

    @Override
    public boolean isExists(String username) {
        return userRepository.findByUsername(username)
                             .isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
            return userRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("There's no user with such id"));
    }

    @Override
    public User findUserTickets(long id) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no user with such id");
        }

        return userRepository.findUserTickets(id)
                             .orElseThrow(() -> new ResourceNotFoundException("Server error"));
    }

    @Override
    public boolean isExists(long id) {
        return userRepository.findById(id)
                             .isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUsernameById(long id, String username) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no user with such id");
        }

        if(isExists(username)) {
            throw new ResourseNotExistsException("Username is taken");
        }

        return new User(id, username);
    }

    @Override
    @Transactional
    public void removeById(int id) {
        userRepository.removeById(id);
    }

}
