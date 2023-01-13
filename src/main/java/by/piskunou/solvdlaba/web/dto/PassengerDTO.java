package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.Passenger;
import by.piskunou.solvdlaba.domain.Passport;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public class PassengerDTO {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Country country;

    private String firstname;
    private String surname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Passport passport;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate dateOfBirth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Passenger.Age age;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Passenger.Gender gender;

}
