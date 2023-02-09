package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.Passenger;
import by.piskunou.solvdlaba.domain.Passport;
import by.piskunou.solvdlaba.persistence.PassengerRepository;
import by.piskunou.solvdlaba.service.impl.PassengerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private PassengerRepository repository;

    @InjectMocks
    private PassengerServiceImpl service;

    @Test
    void verifyCreateTest() {
        Passenger passenger = Passenger.builder()
                                       .country(new Country(1L, "Belarus"))
                                       .firstname("Ronald")
                                       .surname("Lee")
                                       .passport(new Passport(1L, "1234", "1234"))
                                       .dateOfBirth(LocalDate.of(2004, 5, 31))
                                       .age(Passenger.Age.ADULT)
                                       .gender(Passenger.Gender.MALE)
                                       .build();
        assertEquals(passenger, service.create(passenger));
        verify(repository).create(passenger);
    }

    @Test
    void verifyIsExistsTest() {
        service.isExists(1);
        verify(repository).isExistsById(1);
    }

}