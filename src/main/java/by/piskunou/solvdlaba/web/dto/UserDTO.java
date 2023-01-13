package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Null(groups = onCreate.class, message = "Id should be null")
    @NotNull(groups = onUpdate.class, message = "Id should be not null")
    private Long id;

    @NotBlank(groups = onCreate.class, message = "Username should be not blank")
    @Size(max = 50, groups = {onUpdate.class, onCreate.class}, message = "The username must be less than 50 characters")
    private String username;

}
