package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreateException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotUpdatedException;
import by.piskunou.solvdlaba.repository.AirlineRepository;
import by.piskunou.solvdlaba.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Airline findById(long id) {
        Optional<Airline> airline = airlineRepository.findById(id);
        if(airline.isEmpty()) {
            throw new ResourceNotFoundException("There's no airline with such id");
        }

        return airline.get();
    }

    @Override
    @Transactional
    public Airline create(Airline airline) {
        Optional<Airline> airlineOptional = airlineRepository.findByName(airline.getName());
        if(airlineOptional.isPresent()) {
            throw new ResourceNotCreateException("Airline with such name has already exists");
        }

        airlineOptional = airlineRepository.create(airline);

        return airlineOptional.orElseThrow(() -> new ResourceNotCreateException("Server error"));
    }

    @Override
    @Transactional
    public Airline updateNameById(long id, String name) {
        findById(id);
        Optional<Airline> airline = airlineRepository.findByName(name);
        if(airline.isPresent()) {
            throw new ResourceNotUpdatedException("Airline with such name has already exists");
        }

        airline = airlineRepository.updateNameById(id, name);

        return airline.orElseThrow(() -> new ResourceNotUpdatedException("Server error"));
    }

    @Override
    @Transactional
    public void removeById(long id) {
        airlineRepository.removeById(id);
    }
}
