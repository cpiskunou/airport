package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.enums.Age;
import by.piskunou.solvdlaba.domain.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Passenger {

    private Long id;

    private String firstname;

    private String surname;

    private Passport passport;

    private LocalDate dateOfBirth;

    private Age age;

    private Sex sex;

    private Country country;
}
