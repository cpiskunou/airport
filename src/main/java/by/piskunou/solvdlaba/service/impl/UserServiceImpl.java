package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.UserRepository;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService { //UserDetailsService {

    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id, boolean withTickets) {
        Optional<User> user = withTickets ? repository.findByIdWithTickets(id) : repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotExistsException("There's no user with such id"));
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) {
//        User user = userRepository.findByUsername(username)
//                                  .orElseThrow(() -> new UsernameNotFoundException("There's no user with such username"));
//        return new UserDetailsImpl(user);
//    }

    @Override
    @Transactional(readOnly = true)
    public List<User> search(User user) {
        setSearchValue(user::getUsername, user::setUsername);
        return repository.search(user);
    }

    @Override
    @Transactional
    public User create(User user) {
//        user.setRole(User.Role.USER);
        checkIsEntityValid(user);
        user.setRole(User.Role.USER);
        repository.create(user);
        return user;
    }

    @Override
    @Transactional
    public User updateById(long id, User user) {
        if(!isExists(id)) {
            throw new ResourceNotExistsException("There's no user with such id");
        }
        user.setId(id);
        checkIsEntityValid(user);
        repository.update(user);
        return user;
    }

    @Override
    @Transactional
    public void removeById(int id) {
        repository.removeById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExists(long id) {
        return repository.isExistsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExists(long id, String username) {
        return repository.isExistsByUsername(id, username);
    }

    private void setSearchValue(Supplier<String> getter, Consumer<String> setter) {
        if(getter.get() != null) {
            setter.accept('%' + getter.get() + '%');
        } else {
            setter.accept("%%");
        }
    }

    private void checkIsEntityValid(User user) {
        long id = user.getId() != null ? user.getId() : 0;
        if(isExists(id, user.getUsername())) {
            throw new ResourceAlreadyExistsException("Such username has already exists");
        }
    }

}
