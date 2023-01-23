package by.piskunou.solvdlaba.service;


import by.piskunou.solvdlaba.domain.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    List<Airport> findAll();

    Airport findById(long id);

}
