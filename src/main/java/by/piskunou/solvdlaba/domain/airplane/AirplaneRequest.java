package by.piskunou.solvdlaba.domain.airplane;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneRequest {

    private String modelInquiry;
    private short seatAmount;

}
