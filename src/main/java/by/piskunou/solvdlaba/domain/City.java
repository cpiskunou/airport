package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class City {

    private Long id;
    private String name;
    private Country country;
    private List<Airport> airports;

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
