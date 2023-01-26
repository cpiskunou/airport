package by.piskunou.solvdlaba.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountryDTO {

    @Null(message = "Id should be null")
    private Long id;

    @NotBlank(message = "Country name should be not blank")
    @Size(max = 50, message = "Country name should be less than 50 characters")
    private String name;

}
