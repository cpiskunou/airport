package by.piskunou.solvdlaba.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeatDTO {

    private Long number;
    private String place;
    private Boolean free;

}
