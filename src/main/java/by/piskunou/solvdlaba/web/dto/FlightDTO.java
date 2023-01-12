package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.web.groups.onCreate;
import by.piskunou.solvdlaba.web.groups.onUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Null(groups = onCreate.class)
    @NotNull(groups = onUpdate.class)
    private Long id;

    @NotNull(groups = onCreate.class)
    private AirportDTO from;

    @NotNull(groups = onCreate.class)
    private AirportDTO to;

    @NotNull(groups = onCreate.class)
    private AirplaneDTO airplane;

    @NotNull(groups = onCreate.class)
    private AirlineDTO airline;

    @NotNull(groups = onCreate.class)
    private BigDecimal price;

    @NotNull(groups = onCreate.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future
    private LocalDateTime departureTime;

    @NotNull(groups = onCreate.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future
    private LocalDateTime arrivalTime;

    @Null(groups = onCreate.class)
    private List<SeatDTO> seats;

}
