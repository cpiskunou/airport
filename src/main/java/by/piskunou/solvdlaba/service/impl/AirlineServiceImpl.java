package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreatedException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotUpdatedException;
import by.piskunou.solvdlaba.persistent.impl.AirlineRepositoryImpl;
import by.piskunou.solvdlaba.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepositoryImpl airlineRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Airline findById(long id) {
        return airlineRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("There's no airline with such id"));
    }

    @Override
    @Transactional
    public Airline create(Airline airline) {
        if(isExists(airline.getName())) {
            throw new ResourceNotCreatedException("Airline with such name has already exists");
        }

        Long id = airlineRepository.create(airline)
                                   .orElseThrow(() -> new ResourceNotCreatedException("Server error"));
        airline.setId(id);

        return airline;
    }

    @Override
    public boolean isExists(String name) {
        return airlineRepository.findByName(name)
                                .isPresent();
    }

    @Override
    @Transactional
    public Airline updateNameById(long id, String name) {
        if(!isExists(id)) {
            throw new ResourceNotCreatedException("Server error");
        }

        if(isExists(name)) {
            throw new ResourceNotUpdatedException("Airline with such name has already exists");
        }

        return airlineRepository.updateNameById(id, name)
                                .orElseThrow(() -> new ResourceNotUpdatedException("Server error"));
    }

    @Override
    public boolean isExists(long id) {
        return airlineRepository.findById(id)
                                .isPresent();
    }

    @Override
    @Transactional
    public void removeById(long id) {
        airlineRepository.removeById(id);
    }

}
