package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.exception.ResourseAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourseNotExistsException;
import by.piskunou.solvdlaba.persistent.impl.CountryRepositoryImpl;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepositoryImpl countryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findCountryCities(long id) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        return countryRepository.findCountryCities(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Server error"));
    }

    @Override
    public boolean isExists(long id) {
        return countryRepository.findById(id)
                                .isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findCountryAirports(long id) {
        if(!isExists(id)) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        return countryRepository.findCountryAirports(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Server error"));
    }


    @Override
    @Transactional(readOnly = true)
    public Country findById(long id) {
        return countryRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("There's no country with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByName(String name) {
        return countryRepository.findByName(name)
                                .orElseThrow(() -> new ResourceNotFoundException("There's no country with such name"));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        if(isExists(country.getName())) {
            throw new ResourseAlreadyExistsException("Country with such name has already exists");
        }

        countryRepository.create(country);

        return country;
    }

    @Override
    public boolean isExists(String name) {
        return countryRepository.findByName(name)
                                .isPresent();
    }

    @Override
    @Transactional
    public Country updateNameById(long id, String name) {
        if(!isExists(id)){
            throw new ResourceNotFoundException("There's no country with such id");
        }

        if(isExists(name)) {
            throw new ResourseNotExistsException("Country with such name has already exists");
        }

        return new Country(id, name);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        countryRepository.removeById(id);
    }

}
