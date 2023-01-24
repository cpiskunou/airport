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

    private Long number;
    private String place;
    private boolean free;

    public Seat(String place) {
        this.place = place;
        free = true;
    }

}
