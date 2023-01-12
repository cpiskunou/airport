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

    @Null(groups = onCreate.class)
    @NotNull(groups = onUpdate.class)
    private Long id;

    @NotBlank(groups = onCreate.class)
    @Size(max = 300, groups = {onUpdate.class, onCreate.class}, message = "The username must be less than 300 characters")
    private String username;

}
