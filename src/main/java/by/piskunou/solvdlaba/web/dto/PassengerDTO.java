package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.Passenger;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class PassengerDTO {

    @Null(message = "Id should be null")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Country country;

    private String firstname;
    private String surname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PassportDTO passport;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Passenger.Age age;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Passenger.Gender gender;

}
