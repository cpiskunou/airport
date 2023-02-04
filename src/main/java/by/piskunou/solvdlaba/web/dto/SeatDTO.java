package by.piskunou.solvdlaba.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeatDTO {

    @NotNull(message = "Number should be not null")
    private Integer number;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String place;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean free;

}
