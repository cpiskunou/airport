package by.piskunou.solvdlaba.domain.classes;

import java.time.LocalDateTime;

public class Flight {
    private long id;
    private Airport from;
    private Airport to;
    private LocalDateTime DepartureTime;
    private LocalDateTime ArrivalTime;
    private Airplane airplane;
}
