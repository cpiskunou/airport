package by.piskunou.solvdlaba.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    private Long id;
    private Airport from;
    private Airport to;
    private Airplane airplane;
    private Airline airline;
    private BigDecimal price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<Seat> seats;

    public Flight(long id, BigDecimal price, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.price = price;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Flight(long id, LocalDateTime departureTime) {
        this.id = id;
        this.departureTime = departureTime;
    }

}
