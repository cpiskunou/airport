package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
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
public class CountryDTO {

    @Null(groups = onCreate.class, message = "Id should be null")
    @NotNull(groups = onUpdate.class, message = "Id should be not null")
    private Long id;

    @NotBlank(groups = {onUpdate.class, onCreate.class}, message = "Country name should be not blank")
    @Size(max = 50, message = "Country name should be less than 50 characters")
    private String name;

    @Null(groups = onCreate.class, message = "List of cities should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<City> cities;

}
