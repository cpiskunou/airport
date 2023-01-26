package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.web.groups.onCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Schema(description = "User info")
public class UserDTO {

    public interface onRegister {};
    public interface onAuth {};

    @Null(groups = {onRegister.class, onAuth.class}, message = "Id should be null")
    @NotNull(message = "Id should be not null")
    private Long id;

    @NotBlank(groups = {onRegister.class, onAuth.class}, message = "Username should be not blank")
    @Size(max = 50, groups = {onCreate.class}, message = "The username must be less than 50 characters")
    private String username;

    @NotBlank(groups = {onRegister.class, onAuth.class}, message = "Password should be not null")
    @Size(max = 300, groups = {onRegister.class, onAuth.class}, message = "Password should be less than 300 characters")
    private String password;

    @Null(groups = onCreate.class, message = "Tickets should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Ticket> tickets;

}
