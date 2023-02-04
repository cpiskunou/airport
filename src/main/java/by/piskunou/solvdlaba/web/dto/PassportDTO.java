package by.piskunou.solvdlaba.web.dto;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassportDTO {

    @Null(message = "Id should be null")

    private Long id;
    private String number;
    private String identificationNo;

}
