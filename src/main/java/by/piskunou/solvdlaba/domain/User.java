package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private List<Ticket> tickets;

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

}
