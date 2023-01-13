package by.piskunou.solvdlaba.web.mapper.impl;

import by.piskunou.solvdlaba.domain.FlightRequest;
import by.piskunou.solvdlaba.domain.Passenger;
import by.piskunou.solvdlaba.web.dto.FlightRequestDTO;
import by.piskunou.solvdlaba.web.mapper.FlightRequestMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FlightRequestMapperImpl implements FlightRequestMapper {

    @Override
    public FlightRequest toEntity(FlightRequestDTO dto) {
        if(dto == null) {
            return null;
        }

        FlightRequest flightRequest = new FlightRequest();

        String[] fromAirports = dto.getFromAirport()
                                   .split("-");
        String[] toAirports = dto.getToAirport()
                                 .split("-");
        String[] passengersAmount = dto.getPassengers()
                                       .split("-");

        flightRequest.setFromAirports( parseAirports( fromAirports ) );
        flightRequest.setToAirports( parseAirports( toAirports ) );
        flightRequest.setPassengers( parsePassengers( passengersAmount ) );
        flightRequest.setDepartureDate( dto.getDepartureDate() );
        flightRequest.setArrivalDate( dto.getArrivalDate() );

        return flightRequest;
    }

    private Long[] parseAirports(String[] airports) {
        Long[] ids = new Long[airports.length];
        return Arrays.stream(airports)
                .map(id -> Long.valueOf(id))
                .toList()
                .toArray(ids);
    }

    private List<Passenger> parsePassengers(String[] passengersAmount) {
        List<Passenger> passengers = new ArrayList<>( passengersAmount.length );
        int j = 0;
        for(String n : passengersAmount) {
            for(short i = 0; i < Short.parseShort(n); i++) {
                Passenger passenger = new Passenger( Passenger.Age.values()[j] );
                passengers.add(passenger);
            }
            j++;
        }
        return passengers;
    }

}
