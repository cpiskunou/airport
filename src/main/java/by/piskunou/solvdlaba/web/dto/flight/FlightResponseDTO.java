package by.piskunou.solvdlaba.web.dto.flight;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public class FlightResponseDTO {

    public FlightDTO toFlight;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public FlightDTO backFlight;

    public BigDecimal price;

}
