package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Passport;
import by.piskunou.solvdlaba.persistence.PassportRepository;
import by.piskunou.solvdlaba.service.impl.PassportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassportServiceTest {

    @Mock
    private PassportRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private PassportServiceImpl service;

    @Test
    void verifyCreateTest() {
        Passport passport = Passport.builder()
                                    .id(6L)
                                    .number("1234")
                                    .identificationNo("1234")
                                    .build();
        service.create(passport);
        verify(encoder, times(2)).encode(any());
        assertEquals(encoder.encode("1234"), passport.getNumber());
        assertEquals(encoder.encode("1234"), passport.getIdentificationNo());
        verify(repository).create(passport);
    }

}