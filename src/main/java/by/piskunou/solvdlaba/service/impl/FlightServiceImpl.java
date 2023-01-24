package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.*;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.FlightRepository;
import by.piskunou.solvdlaba.service.AirplaneService;
import by.piskunou.solvdlaba.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirplaneService airplaneService;

    @Override
    @Transactional(readOnly = true)
    public Flight findById(long id) {
       return flightRepository.findById(id)
                              .orElseThrow(() -> new ResourceNotExistsException("There's no flight with such id"));
    }

    @Override
    @Transactional
    public Flight create(Flight flight) {
        Airplane airplane = airplaneService.findById(flight.getAirplane()
                                                            .getId());
        List<Seat> seats = createSeats(airplane);
        flight.setSeats(seats);

        flightRepository.create(flight);
        return flight;
    }

    private List<Seat> createSeats(Airplane airplane) {
        byte seatInRow = airplane.getSeatsInRow();
        short rowNo = airplane.getRowNo();

        List<Seat> seats = new ArrayList<>(seatInRow * rowNo);

        for(short i = 1; i <= rowNo; i++) {
            for(byte j = 1; j <= seatInRow; j++) {
                char c = (char)(64 + j);
                Seat seat = new Seat(Short.toString(i) + c);
                seats.add(seat);
            }
        }
        return seats;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightResponse> search(FlightRequest flightRequest) {
        List<Passenger> passengers = flightRequest.getPassengers();

        List<Flight> toFlights = flightRepository.search(
                flightRequest.getFromAirports(),
                flightRequest.getToAirports(),
                flightRequest.getDepartureDate().atStartOfDay(),
                flightRequest.getDepartureDate().atTime(LocalTime.MAX));
        List<BigDecimal> toPrices = cost(passengers, toFlights);

        List<Flight> backFlights = null;
        List<BigDecimal> backPrices = null;

        if(flightRequest.getArrivalDate() != null) {
            backFlights = flightRepository.search(
                    flightRequest.getToAirports(),
                    flightRequest.getFromAirports(),
                    flightRequest.getArrivalDate().atStartOfDay(),
                    flightRequest.getArrivalDate().atTime(LocalTime.MAX));
            backPrices = cost(passengers, backFlights);
        }
        List<FlightResponse> responses = buildFlightResponse(toFlights, toPrices, backFlights, backPrices);
        return responses;
    }

    @Override
    public List<Seat> freeSeats(long id) {
        return flightRepository.freeSeats(id)
                               .orElseThrow(() -> new ResourceNotExistsException("There's no flight with such id"))
                               .getSeats();
    }

    @Override
    public void bookSeat(String number) {
        flightRepository.bookSeat(number);
    }

    @Override
    public boolean isExists(long id) {
        return flightRepository.isExistsById(id);
    }

    private List<FlightResponse> buildFlightResponse(List<Flight> toFlights, List<BigDecimal> toPrices,
                                                     List<Flight> backFlights, List<BigDecimal> backPrices) {
        List<FlightResponse> responses = new ArrayList<>();
        if(backFlights == null) {
            for(int i = 0; i < toFlights.size(); i++) {
                FlightResponse response = new FlightResponse();

                response.setToFlight(toFlights.get(i));
                response.setBackFlight(null);
                response.setPrice(toPrices.get(i));

                responses.add(response);
            }
            return responses;
        }

        for(int i = 0; i < toFlights.size(); i++) {
            for(int j = 0; j < backFlights.size(); j++) {
                FlightResponse response = new FlightResponse();

                response.setToFlight(toFlights.get(i));
                response.setBackFlight(backFlights.get(j));
                response.setPrice(toPrices.get(i).add(backPrices.get(j)));

                responses.add(response);
            }
        }
        return responses;
    }

    private List<BigDecimal> cost(List<Passenger> passengers, List<Flight> flights) {
        List<BigDecimal> prices = new ArrayList<>();
        for(Flight flight: flights) {
            double flightPrice = flight.getPrice().doubleValue();
            double totalPrice = 0;
            for(Passenger passenger: passengers) {
                if(passenger.getAge() == Passenger.Age.CHILD) {
                    totalPrice += flightPrice * 0.2;
                } else if (passenger.getAge() == Passenger.Age.INFANT) {
                    totalPrice += flightPrice * 0.4;
                }
                totalPrice += flightPrice;
            }
            prices.add(new BigDecimal(totalPrice));
        }
        return prices;
    }

}
