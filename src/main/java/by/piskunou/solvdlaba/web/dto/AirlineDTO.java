package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDTO {

    @Null(message = "Id should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotBlank(message = "Name should be not blank")
    @Size(max = 50, message = "Name should be less than 50 characters")
    private String name;

    @NotNull(message = "IATA code should be not null")
    @Size(max = 2, message = "IATA code should be less than 2 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String iata;

    @NotNull(message = "ICAO code should be not null")
    @Size(max = 3, message = "ICAO code should be less than 3 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icao;

    @NotNull(message = "Callsign should be not null")
    @Size(max = 30, message = "Callsign should be less than 30 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String callsign;

}
