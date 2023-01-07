package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.UserNotRegisteredException;
import by.piskunou.solvdlaba.repository.UserRepository;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        if(findByUsernameAndByIdNot(user.getId(), user.getUsername()) != null) {
            throw new UserNotRegisteredException("Username is taken");
        }
        Optional<User> user = userRepository.create(user);

        return user;
    }
    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("There's no user with such id");
        }

        return user.get();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("There's no user with such id");
        }

        return user.get();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameAndByIdNot(long id, String username) {
        Optional<User> user = userRepository.findByUsernameAndByIdNot(id, username);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("There's no user with such id");
        }

        return user.get();
    }
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public void removeById(int id) {
        userRepository.removeById(id);
    }
}
