package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.domain.exception.ResourseAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourseNotExistsException;
import by.piskunou.solvdlaba.persistent.impl.CityRepositoryImpl;
import by.piskunou.solvdlaba.service.CityService;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return cityRepository.findCityAirports(id)
                             .orElseThrow(() -> new ResourseNotExistsException("There's no city with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public City findById(long id) {
        return cityRepository.findById(id)
                             .orElseThrow(() -> new ResourseNotExistsException("There's no city with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public City findByName(String name) {
        return cityRepository.findByName(name)
                             .orElseThrow(() -> new ResourseNotExistsException("There's no city with such name"));
    }

    @Override
    @Transactional
    public City create(City city, long countryId) {
        if(countryService.isExists(countryId)) {
            throw new ResourseNotExistsException("There's no country with such id");
        }

        if(isExists(city.getName())) {
            throw new ResourseAlreadyExistsException("City with such name has already exists");
        }

        cityRepository.create(city, countryId);

        return city;
    }

    @Override
    @Transactional
    public City updateNameById(long id, String name) {
        if(!isExists(id)) {
            throw new ResourseNotExistsException("There's no city with such id");
        }

        if(isExists(name)) {
            throw new ResourseNotExistsException("City with such name has already exists");
        }

        return new City(id, name);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        cityRepository.removeById(id);
    }

    @Override
    public boolean isExists(long id) {
        return cityRepository.findById(id)
                             .isPresent();
    }

    @Override
    public boolean isExists(String name) {
        return cityRepository.findByName(name)
                             .isPresent();
    }

}
