package by.piskunou.solvdlaba.domain.classes;

import by.piskunou.solvdlaba.domain.enums.Age;

import java.util.List;

public class Person {
    private long id;
    private String username;
    private Age age;
    private List<Ticket> tickets;
}
