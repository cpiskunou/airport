package by.piskunou.solvdlaba.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airplane {

    private Long id;
    private String model;
    private Byte seatsInRow;
    private Short rowNo;

}
