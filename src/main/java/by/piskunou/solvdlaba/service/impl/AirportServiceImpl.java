package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.domain.exception.InvalidResourceParamException;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.AirportRepository;
import by.piskunou.solvdlaba.service.AirportService;
import by.piskunou.solvdlaba.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final CityService cityService;
    private final AirportRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Airport> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Airport findById(long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResourceNotExistsException("There's no airplane with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Airport> search(Airport airport) {
        airport.setName('%' + airport.getName() + '%');
        airport.setIata('%' + airport.getIata() + '%');
        airport.setIcao('%' + airport.getIcao() + '%');
        return repository.search(airport);
    }

    @Override
    @Transactional
    public Airport create(long cityId, Airport airport) {
        if(!cityService.isExists(cityId)) {
            throw new ResourceNotExistsException("There's is no city with such id");
        }
        if(isExists(airport.getName())) {
            throw new ResourceAlreadyExistsException("Airport with such name has already exists");
        }
        if(!isValidIata(airport.getIata())) {
            throw new InvalidResourceParamException("Invalid IATA code");
        }
        if(!isValidIcao(airport.getIcao())) {
            throw new InvalidResourceParamException("Invalid ICAO code");
        }
        repository.create(cityId, airport);
        return airport;
    }

    @Override
    public Airport updateNameById(long id, String updatedName) {
        if(!isExists(id)) {
            throw new ResourceNotExistsException("There's is no airport with such id");
        }
        if(isExists(updatedName)) {
            throw new ResourceAlreadyExistsException("Airport with such name has already exists");
        }
        repository.updateNameById(id, updatedName);
        return new Airport(id, updatedName);
    }

    @Override
    public Airport updateNameByCode(String code, String updatedName) {
        if(!isValidIata(code) || !isValidIcao(code)) {
            throw new InvalidResourceParamException("Invalid designator");
        }
        if(isExists(updatedName)) {
            throw new ResourceAlreadyExistsException("Airport with such name has already exists");
        }
        repository.updateNameByCode(code, updatedName);
        return new Airport(updatedName);
    }

    @Override
    public void removeById(long id) {
        repository.removeById(id);
    }

    @Override
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

}
