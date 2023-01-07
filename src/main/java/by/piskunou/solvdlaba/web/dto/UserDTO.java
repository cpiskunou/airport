package by.piskunou.solvdlaba.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    @NotBlank
    @Size(max = 300, message = "The username must be less than 300 characters")
    private String username;
}
