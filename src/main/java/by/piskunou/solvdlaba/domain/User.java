package by.piskunou.solvdlaba.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;

    //TODO: see jakarta.validation.constraints.NotBlank.message
    @NotBlank
    @Size(max = 300, message = "The username must be less than 300 characters")
    private String username;

    private List<Ticket> tickets;

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }
}
