package by.piskunou.solvdlaba.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneDTO {
    private long id;
    private String model;
    private short seatAmount;
}
