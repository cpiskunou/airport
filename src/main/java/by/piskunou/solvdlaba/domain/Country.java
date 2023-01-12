package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Country {

    private Long id;
    private String name;
    private List<City> cities;

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
