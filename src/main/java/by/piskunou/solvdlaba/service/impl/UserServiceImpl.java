package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistent.UserRepository;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.security.UserDetailsImpl;
import by.piskunou.solvdlaba.service.TicketService;
import by.piskunou.solvdlaba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final TicketService ticketService;

    @Override
    @Transactional
    public User register(User user) {
        if (isExists(user.getUsername())) {
            throw new ResourceAlreadyExistsException( "Username is taken");
        }
        userRepository.register(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
            return userRepository.findById(id)
                                 .orElseThrow(() -> new ResourceNotExistsException("There's no user with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new ResourceNotExistsException("There's no user with such username"));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException("There's no user with such username"));
        return new UserDetailsImpl(user);
    }

    @Override
    public User findUserTickets(long id) {
        return userRepository.findUserTickets(id)
                             .orElseThrow(() -> new ResourceNotExistsException("There's no user with such id"));
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
            throw new ResourceNotExistsException("There's no user with such id");
        }
        if(isExists(username)) {
            throw new ResourceAlreadyExistsException("Username is taken");
        }
        return new User(id, username);
    }

    @Override
    @Transactional
    public void removeById(int id) {
        userRepository.removeById(id);
    }

    @Override
    @Transactional
    public User buyTicket(long id, Ticket ticket) {
        if(!isExists(id)) {
            throw new ResourceNotExistsException("There's no user with such id");
        }
        ticketService.appointOwner(ticket, id);
        return findUserTickets(id);
    }


    @Override
    public boolean isExists(String username) {
        return userRepository.isExistsByUsername(username);
    }

    @Override
    public boolean isExists(long id) {
        return userRepository.isExistsById(id);
    }
}
