package by.piskunou.solvdlaba.web.dto.airplane;

import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirplaneRequestDTO {

    private String modelInquiry;

    @Max(value = Short.MAX_VALUE, message = "Amount of seats should be less than 32767")
    private Short seatAmount;

}
