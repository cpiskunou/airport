package by.piskunou.solvdlaba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    private Long id;

    private Airport from;

    private Airport to;

    private Airplane airplane;

    private Airline airline;

    private LocalDateTime DepartureTime;

    private LocalDateTime ArrivalTime;

    private List<String> freeSeats;
}
