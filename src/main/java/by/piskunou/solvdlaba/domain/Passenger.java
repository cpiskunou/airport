package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.enums.Age;
import by.piskunou.solvdlaba.domain.enums.Sex;

import java.time.LocalDate;
import java.util.List;

public class Passenger {
    private long id;
    private String name;
    private String surname;
    private Passport passport;
    private LocalDate dateOfBirth;
    private Age age;
    private Sex sex;
    private List<Ticket> tickets;
}
