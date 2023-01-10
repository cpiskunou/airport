package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreateException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotUpdatedException;
import by.piskunou.solvdlaba.repository.CityRepository;
import by.piskunou.solvdlaba.service.CityService;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryService countryService;

    @Override
    @Transactional(readOnly = true)
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public City findCityAirports(long id) {
        Optional<City> city = cityRepository.findById(id);
        if(city.isEmpty()) {
            throw new ResourceNotFoundException("There's no city with such id");
        }

        city = cityRepository.findCityAirports(id);
        return city.get();
    }

    @Override
    @Transactional(readOnly = true)
    public City findById(long id) {
        Optional<City> city = cityRepository.findById(id);
        if(city.isEmpty()) {
            throw new ResourceNotFoundException("There's no city with such id");
        }

        return city.get();
    }

    @Override
    @Transactional(readOnly = true)
    public City findByName(String name) {
        Optional<City> city = cityRepository.findByName(name);
        if(city.isEmpty()) {
            throw new ResourceNotFoundException("There's no city with such name");
        }

        return city.get();
    }

    @Override
    @Transactional
    public City create(City city, long countryId) {
        countryService.findById(countryId);

        Optional<City> cityOptional = cityRepository.findByName(city.getName());
        if(cityOptional.isPresent()) {
            throw new ResourceNotCreateException("City with such name has already exists");
        }

        cityOptional = cityRepository.create(city, countryId);

        return cityOptional.get();
    }

    @Override
    @Transactional
    public City updateNameById(long id, String name) {
        findById(id);
        Optional<City> city = cityRepository.findByName(name);
        if(city.isPresent()) {
            throw new ResourceNotUpdatedException("City with such name has already exists");
        }

        city = cityRepository.updateNameById(id, name);

        return city.orElseThrow(() -> new ResourceNotUpdatedException("Server error"));
    }

    @Override
    @Transactional
    public void removeById(long id) {
        cityRepository.removeById(id);
    }

    @Override
    @Transactional
    public void removeByName(String name) {
        cityRepository.removeByName(name);
    }
}
