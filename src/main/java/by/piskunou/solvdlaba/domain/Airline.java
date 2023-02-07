package by.piskunou.solvdlaba.domain;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Hidden
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

    private Long id;
    private String name;
    private String iata;
    private String icao;
    private String callsign;

    public Airline(String name) {
        this.name = name;
    }

    public Airline(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
