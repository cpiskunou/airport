package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistent.CountryRepository;
import by.piskunou.solvdlaba.persistent.impl.CountryRepositoryImpl;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Country findCountryCities(long countryId) {
        return countryRepository.findCountryCities(countryId)
                                .orElseThrow(() -> new ResourceNotExistsException("There's no country with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Country findCountryAirports(long countryId) {
        return countryRepository.findCountryAirports(countryId)
                                .orElseThrow(() -> new ResourceNotExistsException("There's no country with such id"));
    }


    @Override
    @Transactional(readOnly = true)
    public Country findById(long id) {
        return countryRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotExistsException("There's no country with such id"));
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByName(String name) {
        return countryRepository.findByName(name)
                                .orElseThrow(() -> new ResourceNotExistsException("There's no country with such name"));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        if(isExists(country.getName())) {
            throw new ResourceAlreadyExistsException("Country with such name has already exists");
        }
        countryRepository.create(country);
        return country;
    }

    @Override
    @Transactional
    public Country updateNameById(long id, String name) {
        if(!isExists(id)){
            throw new ResourceNotExistsException("There's no country with such id");
        }
        if(isExists(name)) {
            throw new ResourceNotExistsException("Country with such name has already exists");
        }
        countryRepository.updateNameById(id, name);
        return new Country(id, name);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        countryRepository.removeById(id);
    }

    @Override
    public boolean isExists(long id) {
        return countryRepository.isExists(id);
    }

    @Override
    public boolean isExists(String name) {
        return countryRepository.isExists(name);
    }

}
