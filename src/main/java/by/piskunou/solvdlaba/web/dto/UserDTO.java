package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.enums.Authority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private Authority authority;
}
