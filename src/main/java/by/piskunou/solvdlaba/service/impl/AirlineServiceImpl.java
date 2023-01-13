package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistent.AirlineRepository;
import by.piskunou.solvdlaba.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return airlineRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotExistsException("There's no airline with such id"));
    }

    @Override
    @Transactional
    public Airline create(Airline airline) {
        if(isExists(airline.getName())) {
            throw new ResourceAlreadyExistsException("Airline with such name has already exists");
        }
        airlineRepository.create(airline);
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
        airlineRepository.updateNameById(id, name);
        return new Airline(id, name);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        airlineRepository.removeById(id);
    }

    @Override
    public boolean isExists(String name) {
        return airlineRepository.isExists(name);
    }

    @Override
    public boolean isExists(long id) {
        return airlineRepository.isExists(id);
    }

}
