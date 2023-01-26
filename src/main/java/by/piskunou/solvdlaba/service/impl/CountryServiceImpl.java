package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.CountryRepository;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Country findById(long id) {
        return repository.findById(id)
                                .orElseThrow(() -> new ResourceNotExistsException("There's no country with such id"));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        if(isExists(country.getName())) {
            throw new ResourceAlreadyExistsException("Country with such name has already exists");
        }
        repository.create(country);
        return country;
    }

    @Override
    @Transactional
    public Country updateNameById(long id, String updatedName) {
        if(!isExists(id)){
            throw new ResourceNotExistsException("There's no country with such id");
        }
        if(isExists(updatedName)) {
            throw new ResourceNotExistsException("Country with such name has already exists");
        }
        repository.updateNameById(id, updatedName);
        return new Country(id, updatedName);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        repository.removeById(id);
    }

    @Override
    public boolean isExists(long id) {
        return repository.isExistsById(id);
    }

    @Override
    public boolean isExists(String name) {
        return repository.isExistsByName(name);
    }

}
