package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
