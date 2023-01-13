package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightDTO {

    @Null(groups = onCreate.class, message = "Id should be null")
    @NotNull(groups = onUpdate.class, message = "Id should be not null")
    private Long id;

    @NotNull(groups = onCreate.class, message = "Airport from should be not null")
    private AirportDTO from;

    @NotNull(groups = onCreate.class, message = "Airport to should be not null")
    private AirportDTO to;

    @NotNull(groups = onCreate.class, message = "Airplane should be not null")
    private AirplaneDTO airplane;

    @NotNull(groups = onCreate.class, message = "Airline should be not null")
    private AirlineDTO airline;

    @NotNull(groups = onCreate.class, message = "Price should be not null")
    private BigDecimal price;

    @NotNull(groups = onCreate.class, message = "Departure time should be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future
    private LocalDateTime departureTime;

    @NotNull(groups = onCreate.class, message = "Arrival time should be not null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future
    private LocalDateTime arrivalTime;

    @Null(groups = onCreate.class, message = "List of seats should be null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SeatDTO> seats;

}
