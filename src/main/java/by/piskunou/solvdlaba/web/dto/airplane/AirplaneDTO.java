package by.piskunou.solvdlaba.web.dto.airplane;

import by.piskunou.solvdlaba.web.groups.onCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirplaneDTO {

    @Null(groups = onCreate.class, message = "Id should be null")
    private Long id;

    @NotBlank(groups = onCreate.class, message = "Model name should be not blank")
    @Size(groups = onCreate.class, max = 300, message = "Model name should be less than 300 characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String model;

    @NotNull(groups = onCreate.class, message = "Amount of seats in row should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Byte seatsInRow;

    @NotNull(groups = onCreate.class, message = "Amount of rows should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Short rowNo;

}
