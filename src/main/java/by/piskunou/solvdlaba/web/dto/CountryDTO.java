package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CountryDTO {
    @Null(groups = onCreate.class)
    @NotNull(groups = onUpdate.class)
    private Long id;

    @NotBlank(groups = {onUpdate.class, onCreate.class})
    private String name;

    @Null(groups = onCreate.class)
    private List<City> cities;
}
