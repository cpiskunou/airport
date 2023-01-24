package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.domain.exception.InvalidResourceParamException;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.AirlineRepository;
import by.piskunou.solvdlaba.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Airline> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Airline findById(long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotExistsException("There's no airline with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Airline findByDesignator(String designator) {
        return repository.findByDesignator(designator)
                         .orElseThrow(() -> new ResourceNotExistsException("There's no airline with such designator"));
    }

    @Override
    @Transactional
    public Airline create(Airline airline) {
        if(isExists(airline.getName())) {
            throw new ResourceAlreadyExistsException("Airline with such name has already exists");
        }
        if(!isValidIata(airline.getIata())) {
            throw new InvalidResourceParamException("Invalid iata");
        }
        if(!isValidIcao(airline.getIcao())) {
            throw new InvalidResourceParamException("Invalid icao");
        }
        if(!isValidIcao(airline.getCallsign())) {
            throw new InvalidResourceParamException("Invalid callsign");
        }
        repository.create(airline);
        return airline;
    }

    @Override
    @Transactional
    public Airline updateNameById(long id, String name) {
        if(!isExists(id)) {
            throw new ResourceNotExistsException("There's no airline with such id");
        }
        if(isExists(name)) {
            throw new ResourceAlreadyExistsException("Airline with such name has already exists");
        }
        repository.updateNameById(id, name);
        return new Airline(id, name);
    }

    @Override
    @Transactional
    public Airline updateNameByDesignator(String designator, String name) {
        if(!isValidIata(designator) || !isValidIcao(designator) || !isValidCallsign(designator)) {
            throw new InvalidResourceParamException("Invalid designator");
        }
        if(isExists(name)) {
            throw new ResourceAlreadyExistsException("Airline with such name has already exists");
        }
        repository.updateNameByDesignator(designator, name);
        return new Airline(name);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        repository.removeById(id);
    }

    @Override
    @Transactional
    public void removeByDesignator(String designator) {
        repository.removeByDesignator(designator);
    }

    @Override
    public boolean isExists(long id) {
        return repository.isExistsById(id);
    }

    @Override
    public boolean isExists(String name) {
        return repository.isExistsByName(name);
    }

    @Override
    public boolean isValidIata(String iata) {
        return true;
    }

    @Override
    public boolean isValidIcao(String icao) {
        return true;
    }

    @Override
    public boolean isValidCallsign(String callsign) {
        return true;
    }

}
