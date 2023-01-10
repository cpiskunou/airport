package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.exception.ResourceNotCreateException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotFoundException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotUpdatedException;
import by.piskunou.solvdlaba.repository.CountryRepository;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findCountryCities(long id) {
        Optional<Country> country = countryRepository.findById(id);
        if(country.isEmpty()) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        country = countryRepository.findCountryCities(id);

        return country.orElseThrow(() -> new ResourceNotFoundException("Server error"));
    }

    @Override
    @Transactional(readOnly = true)
    public Country findCountryAirports(long id) {
        Optional<Country> country = countryRepository.findById(id);
        if(country.isEmpty()) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        country = countryRepository.findCountryAirports(id);

        return country.orElseThrow(() -> new ResourceNotFoundException("Server error"));
    }


    @Override
    @Transactional(readOnly = true)
    public Country findById(long id) {
        Optional<Country> country = countryRepository.findById(id);
        if(country.isEmpty()) {
            throw new ResourceNotFoundException("There's no country with such id");
        }

        return country.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByName(String name) {
        Optional<Country> country = countryRepository.findByName(name);
        if(country.isEmpty()) {
            throw new ResourceNotFoundException("There's no country with such name");
        }

        return country.get();
    }

    @Override
    @Transactional
    public Country create(Country country) {
        Optional<Country> countryOptional = countryRepository.findByName(country.getName());
        if(countryOptional.isPresent()) {
            throw new ResourceNotCreateException("Country with such name has already exists");
        }

        countryOptional = countryRepository.create(country);

        return countryOptional.get();
    }

    @Override
    @Transactional
    public Country updateNameById(long id, String name) {
        findById(id);
        Optional<Country> country = countryRepository.findByName(name);
        if(country.isPresent()) {
            throw new ResourceNotUpdatedException("Country with such name has already exists");
        }

        country = countryRepository.updateNameById(id, name);

        return country.orElseThrow(() -> new ResourceNotUpdatedException("SQLException: Didn't update a country"));
    }

    @Override
    @Transactional
    public void removeById(long id) {
        countryRepository.removeById(id);
    }

    @Override
    @Transactional
    public void removeByName(String name) {
        countryRepository.removeByName(name);
    }
}
