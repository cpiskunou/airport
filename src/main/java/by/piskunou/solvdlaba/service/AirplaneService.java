package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airplane;

import java.util.Optional;

public interface AirplaneService {
    public void save(Airplane airplane);
    public Optional<Airplane> findById(int id);
    public Optional<Airplane> findByModel(String model);
    public void removeById(int id);
    public void removeByModel(String model);
}
