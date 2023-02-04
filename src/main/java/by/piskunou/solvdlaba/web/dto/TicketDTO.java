package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.flight.Flight;
import by.piskunou.solvdlaba.domain.Passenger;
import by.piskunou.solvdlaba.web.dto.flight.FlightDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {

    @Null(message = "Id should be null")
    private Long id;

    @NotNull(message = "Passenger should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PassengerDTO passenger;

    @NotNull(message = "Flight should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private FlightDTO flight;

    @NotNull(message = "Seat should be not null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SeatDTO seat;

}
