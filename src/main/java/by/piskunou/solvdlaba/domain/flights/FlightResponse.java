package by.piskunou.solvdlaba.domain.flights;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {

    public Flight toFlight;
    public Flight backFlight;
    public BigDecimal price;

}
