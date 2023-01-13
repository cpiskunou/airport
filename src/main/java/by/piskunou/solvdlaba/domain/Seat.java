package by.piskunou.solvdlaba.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    private String number;
    private Boolean free = Boolean.TRUE;

    public Seat(String number) {
        this.number = number;
    }

}
