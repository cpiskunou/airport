package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Passenger {

    public enum Gender {
        MALE, FEMALE
    }
    public enum Age {
        ADULT, CHILD, INFANT;
    }

    private Long id;
    private Country country;
    private String firstname;
    private String surname;
    private Passport passport;
    private LocalDate dateOfBirth;
    private Age age;
    private Gender gender;

    public Passenger(Age age) {
        this.age = age;
    }

    public Passenger(long id, String firstname, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
    }
}
