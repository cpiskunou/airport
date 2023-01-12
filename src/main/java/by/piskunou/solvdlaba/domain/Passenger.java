package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private Gender gender;
    private Country country;

    public enum Gender {
        MALE, FEMALE
    }
    public enum Age {
        ADULT, CHILD, INFANT;
    }

    public Passenger(Age age) {
        this.age = age;
    }

}
