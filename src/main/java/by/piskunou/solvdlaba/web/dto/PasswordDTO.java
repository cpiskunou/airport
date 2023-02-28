package by.piskunou.solvdlaba.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Password info")
public class PasswordDTO {

    @Schema(description = "User's password")
    @NotBlank(message = "Password shouldn't be blank")
    @Size(min = 8, max = 300, message = "Password should be between 8 and 300 characters")
    private String password;

    @Schema(description = "Confirmed user's password")
    private String confirmPassword;

}
