package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.enums.Authority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User {
    private long id;
    private String username;
    private Authority authority;
    private List<Ticket> tickets;
}
