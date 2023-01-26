package by.piskunou.solvdlaba.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    private Long id;
    private String name;
    private String iata;
    private String icao;

    public Airport(String name) {
        this.name = name;
    }

    public Airport(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
