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
    @Transactional
    public List<Airline> search(Airline airline) {
        airline.setName('%' + airline.getName() + '%');
        airline.setIata('%' + airline.getIata() + '%');
        airline.setIcao('%' + airline.getIcao() + '%');
        airline.setCallsign('%' + airline.getCallsign() + '%');
        return repository.search(airline);
    }

    @Override
    @Transactional
    public Airline create(Airline airline) {
        if(isExists(airline.getName())) {
            throw new ResourceAlreadyExistsException("Airline with such name has already exists");
        }
        if(!isValidIata(airline.getIata())) {
            throw new InvalidResourceParamException("Invalid IATA");
        }
        if(!isValidIcao(airline.getIcao())) {
            throw new InvalidResourceParamException("Invalid ICAO");
        }
        if(!isValidIcao(airline.getCallsign())) {
            throw new InvalidResourceParamException("Invalid callsign");
        }
        repository.create(airline);
        return airline;
    }

    @Override
    @Transactional
    public Airline updateNameById(long id, String updatedName) {
        if(!isExists(id)) {
            throw new ResourceNotExistsException("There's no airline with such id");
        }
        if(isExists(updatedName)) {
            throw new ResourceAlreadyExistsException("Airline with such name has already exists");
        }
        repository.updateNameById(id, updatedName);
        return new Airline(id, updatedName);
    }

    @Override
    @Transactional
    public Airline updateNameByCode(String code, String updatedName) {
        if(!isValidIata(code) || !isValidIcao(code) || !isValidCallsign(code)) {
            throw new InvalidResourceParamException("Invalid designator");
        }
        if(isExists(updatedName)) {
            throw new ResourceAlreadyExistsException("Airline with such name has already exists");
        }
        repository.updateNameByCode(code, updatedName);
        return new Airline(updatedName);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        repository.removeById(id);
    }

    @Override
    @Transactional
    public void removeByCode(String code) {
        repository.removeByCode(code);
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
