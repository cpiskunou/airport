package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onSearch;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO {

    @Null(message = "Id should be null")
    private Long id;

    @NotBlank(message = "City name should be not blank")
    @Size(max = 50, message = "City name should be less than50 characters")
    private String name;

    @Null(message = "Country should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CountryDTO country;

    @Null(message = "List of airports should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AirportDTO> airports;

}
