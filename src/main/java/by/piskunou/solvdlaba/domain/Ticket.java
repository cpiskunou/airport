package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.enums.Age;

public class Ticket {
    private long id;
    private Flight departureFlight;
    private Flight returnFlight;
    private long price;
    private Age age;
    private User owner;
}
