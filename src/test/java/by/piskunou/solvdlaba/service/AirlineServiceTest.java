package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airline;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.AirlineRepository;
import by.piskunou.solvdlaba.service.impl.AirlineServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {

    @Mock
    private AirlineRepository repository;

    @InjectMocks
    private AirlineServiceImpl service;

    @Test
    void verifyFindAllTest() {
        service.findAll();
        verify(repository).findAll();
    }

    @Test
    void verifyFindByIdTest() {
        Airline airline = Airline.builder()
                                 .id(1L)
                                 .name("Belavia Belarusian Airlines")
                                 .iata("B2")
                                 .icao("BRU")
                                 .callsign("BELAVIA")
                                 .build();
        when(repository.findById(1)).thenReturn(Optional.of(airline));
        assertEquals(airline, service.findById(1));
        verify(repository).findById(1);
    }

    @Test
    void verifyFindByIdFailedTest() {
        assertThrows(ResourceNotExistsException.class, () -> service.findById(Long.MAX_VALUE));
        verify(repository).findById(Long.MAX_VALUE);
    }

    @Test
    void verifySearchTest() {
        Airline airline = new Airline("Mi", "E", "E", null);
        assertNotNull(service.search(airline));

        assertEquals("%Mi%", airline.getName());
        assertEquals("%E%", airline.getIata());
        assertEquals("%E%", airline.getIcao());
        assertEquals("%%", airline.getCallsign());

        verify(repository).search(airline);
    }

    @Test
    void verifyCreateTest() {
        Airline airline =  Airline.builder()
                                  .name("Royal Air Maroc")
                                  .iata("AT")
                                  .icao("RAM")
                                  .callsign("ROYAL AIR MAROC")
                                  .build();

        assertEquals(airline, service.create(airline));
        verify(repository).isExistsByName(0, "Royal Air Maroc");
        verify(repository).isExistsByIata(0, "AT");
        verify(repository).isExistsByIcao(0, "RAM");
        verify(repository).isExistsByCallsign(0, "ROYAL AIR MAROC");
        verify(repository).create(airline);
    }


    @Test
    void verifyCreateNameFailedTest() {
        Airline airline = Airline.builder()
                                 .name("Belavia Belarusian Airlines")
                                 .build();

        when(repository.isExistsByName(0, "Belavia Belarusian Airlines")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> service.create(airline));

        verify(repository).isExistsByName(0, "Belavia Belarusian Airlines");
        verify(repository, times(0)).isExistsByIata(anyLong(), anyString());
        verify(repository, times(0)).isExistsByIcao(anyLong(), anyString());
        verify(repository, times(0)).isExistsByCallsign(anyLong(), anyString());
        verify(repository, times(0)).create(any(Airline.class));
    }

    @Test
    void verifyCreateIataFailedTest() {
        Airline airline = Airline.builder()
                                 .iata("B2")
                                 .build();

        when(repository.isExistsByIata(0, "B2")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> service.create(airline));

        verify(repository).isExistsByName(0, null);
        verify(repository).isExistsByIata(0, "B2");
        verify(repository, times(0)).isExistsByIcao(anyLong(), anyString());
        verify(repository, times(0)).isExistsByCallsign(anyLong(), anyString());
        verify(repository, times(0)).create(any(Airline.class));
    }

    @Test
    void verifyCreateIcaoFailedTest() {
        Airline airline = Airline.builder()
                .icao("BRU")
                .build();

        when(repository.isExistsByIcao(0, "BRU")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> service.create(airline));

        verify(repository).isExistsByName(0, null);
        verify(repository).isExistsByIata(0, null);
        verify(repository).isExistsByIcao(0, "BRU");
        verify(repository, times(0)).isExistsByCallsign(anyLong(), anyString());
        verify(repository, times(0)).create(any(Airline.class));
    }

    @Test
    void verifyCreateCallsignFailedTest() {
        Airline airline = Airline.builder()
                                 .callsign("BELAVIA")
                                 .build();

        when(repository.isExistsByCallsign(0, "BELAVIA")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> service.create(airline));

        verify(repository).isExistsByName(0, null);
        verify(repository).isExistsByIata(0, null);
        verify(repository).isExistsByIcao(0, null);
        verify(repository).isExistsByCallsign(0, "BELAVIA");
        verify(repository, times(0)).create(any(Airline.class));
    }


    @Test
    void verifyUpdateByIdTest() {
        Airline airline = Airline.builder()
                                 .name("Belavia Belarusian Airlines")
                                 .iata("B3")
                                 .icao("BRT")
                                 .callsign("BELAVIA")
                                 .build();
        when(repository.isExistsById(1)).thenReturn(true);

        assertEquals(airline, service.updateById(1, airline));
        verify(repository).isExistsById(1);
        assertEquals(1, airline.getId());
        verify(repository).update(airline);
    }

    @Test
    void verifyUpdateByNonexistentIdTest() {
        Airline airline = Airline.builder()
                                 .name("Belavia Belarusian Airlines")
                                 .iata("B3")
                                 .icao("BRT")
                                 .callsign("BELAVIA")
                                 .build();
        assertEquals(airline, service.updateById(Long.MAX_VALUE, airline));
        verify(repository).isExistsById(Long.MAX_VALUE);
    }

    @Test
    void verifyRemoveByIdTest() {
        service.removeById(anyLong());
        verify(repository).removeById(anyLong());
    }

    @Test
    void verifyIsExistsTest() {
        service.isExists(1);
        verify(repository).isExistsById(1);
    }

    @Test
    void verifyIsExistsByNameTest() {
        service.isExistsByName(1, "Belavia Belarusian Airlines");
        verify(repository).isExistsByName(1, "Belavia Belarusian Airlines");
    }

    @Test
    void verifyIsExistsByIataTest() {
        service.isExistsByIata(1, "B2");
        verify(repository).isExistsByIata(1, "B2");
    }

    @Test
    void verifyIsExistsByIcaoTest() {
        service.isExistsByIcao(0, "BRU");
        verify(repository).isExistsByIcao(0, "BRU");
    }

    @Test
    void verifyIsExistsByCallsignTest() {
        service.isExistsByCallsign(1, "BELAVIA");
        verify(repository).isExistsByCallsign(1, "BELAVIA");
    }


}