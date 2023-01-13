package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.domain.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User findById(long id);

    User findUserTickets(long id);

    List<User> findAll();

    User updateUsernameById(long id, String username);

    void removeById(int id);

    User buyTicket(long id, Ticket ticket);

    boolean isExists(String username);

    boolean isExists(long id);

}
