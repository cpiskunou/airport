package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Flight;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.repository.FlightRepository;
import by.piskunou.solvdlaba.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    @Transactional(readOnly = true)
    public Flight findById(long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isEmpty()) {
            throw new ResourceNotFoundException("There's no flight with such id");
        }

        return flight.get();
    }

    @Override
    @Transactional
    public Flight create(Flight flight) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Flight> search() {
        return flightRepository.search();
    }
}
