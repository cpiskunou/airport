package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreatedException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotUpdatedException;
import by.piskunou.solvdlaba.persistent.impl.CityRepositoryImpl;
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

    private final CityRepositoryImpl cityRepository;
    private final CountryService countryService;

    @Override
    @Transactional(readOnly = true)
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public City findCityAirports(long id) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no city with such id");
        }

        return cityRepository.findCityAirports(id)
                             .orElseThrow(() -> new ResourceNotCreatedException("Server error"));
    }

    @Override
    public boolean isExists(long id) {
        return cityRepository.findById(id)
                             .isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public City findById(long id) {
        return cityRepository.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException("There's no city with such id"));
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
        if(countryService.isExists(countryId)) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        if(isExists(city.getName())) {
            throw new ResourceNotCreatedException("City with such name has already exists");
        }

        Long id = cityRepository.create(city, countryId)
                                .orElseThrow(() -> new ResourceNotUpdatedException("Server error"));
        city.setId(id);

        return city;
    }

    @Override
    public boolean isExists(String name) {
        return cityRepository.findByName(name)
                             .isPresent();
    }


    @Override
    @Transactional
    public City updateNameById(long id, String name) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no city with such id");
        }

        if(isExists(name)) {
            throw new ResourceNotUpdatedException("City with such name has already exists");
        }

        return cityRepository.updateNameById(id, name)
                             .orElseThrow(() -> new ResourceNotUpdatedException("Server error"));
    }

    @Override
    @Transactional
    public void removeById(long id) {
        cityRepository.removeById(id);
    }

}
