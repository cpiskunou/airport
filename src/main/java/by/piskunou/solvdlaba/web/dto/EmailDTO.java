package by.piskunou.solvdlaba.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Email info")
public class EmailDTO {

    @Schema(description = "Email")
    @NotBlank(message = "Email shouldn't be blank")
    @Email(message = "String should be valid email")
    private String email;

}
