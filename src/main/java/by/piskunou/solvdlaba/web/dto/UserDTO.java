package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.Ticket;
import by.piskunou.solvdlaba.domain.User;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
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

    @Null(groups = {onCreate.class, onUpdate.class}, message = "Id should be null")
    private Long id;

    @NotBlank(groups = {onCreate.class, onUpdate.class}, message = "Username should be not blank")
    @Size(groups = {onCreate.class, onUpdate.class}, max = 50, message = "The username must be less than 50 characters")
    private String username;

    @NotBlank(groups = {onCreate.class, onUpdate.class}, message = "Password should be blank")
    @Size(groups = {onCreate.class, onUpdate.class}, max = 300, message = "Password should be less than 300 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @Null(groups = onCreate.class, message = "Role should be null")
    @NotNull(groups = onUpdate.class, message = "Role should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User.Role role;

    @Null(groups = {onCreate.class, onUpdate.class}, message = "Tickets should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Ticket> tickets;

}
