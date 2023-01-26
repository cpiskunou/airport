package by.piskunou.solvdlaba.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntityDTO {

    @NotNull(message = "Access token should be not null")
    private String accessToken;

    @NotNull(message = "Refresh token should be not null")
    private String refreshToken;

}
