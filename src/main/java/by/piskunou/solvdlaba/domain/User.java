package by.piskunou.solvdlaba.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class User {
    private long id;

    @NotBlank
    @Size(max = 300)
    private String username;

    private List<DualTicket> tickets;

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }
}
