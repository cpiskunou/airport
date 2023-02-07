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
    AirlineRepository repository;

    @InjectMocks
    AirlineServiceImpl service;

    @Test
    void verifyFindAllTest() {
        service.findAll();
        verify(repository).findAll();
    }

    @Test
    void verifyFindByIdTest() {
        Airline airline = new Airline(1L, "Belavia Belarusian Airlines", "B2", "BRU", "BELAVIA");
        when(repository.findById(1)).thenReturn(Optional.of(airline));
        assertEquals(airline, service.findById(1));
        verify(repository).findById(1);
    }

    @Test
    void verifyFindByIdFailedTest() {
        when(repository.findById(30)).thenThrow(ResourceNotExistsException.class);
        assertThrows(ResourceNotExistsException.class, () -> service.findById(30));
        verify(repository).findById(30);
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
        assertNull(airline.getId());

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
    void verifyUpdateTest() {
        Airline airline = Airline.builder()
                                 .name("Belavia Belarusian Airlines")
                                 .iata("B3")
                                 .icao("BRT")
                                 .callsign("BELAVIA")
                                 .build();

        when(repository.isExistsById(1)).thenReturn(true);

        service.update(1, airline);

        assertEquals(1, airline.getId());

        verify(repository).isExistsById(1);
        verify(repository, times(0)).create(any());
        verify(repository).isExistsByName(1, "Belavia Belarusian Airlines");
        verify(repository).isExistsByIata(1, "B3");
        verify(repository).isExistsByIcao(1, "BRT");
        verify(repository).isExistsByCallsign(1, "BELAVIA");
        verify(repository).update(airline);

    }

    @Test
    void verifyUpdateNonexistentIdTest() {
        Airline airline = Airline.builder()
                                 .name("Belavia Belarusian Airlines")
                                 .iata("B3")
                                 .icao("BRT")
                                 .callsign("BELAVIA")
                                 .build();

        service.update(30, airline);

        assertNull(airline.getId());

        verify(repository).isExistsById(30);
        verify(repository).create(airline);
        verify(repository).isExistsByName(0, "Belavia Belarusian Airlines");
        verify(repository).isExistsByIata(0, "B3");
        verify(repository).isExistsByIcao(0, "BRT");
        verify(repository).isExistsByCallsign(0, "BELAVIA");
        verify(repository, times(0)).update(any());
    }


    @Test
    void verifyRemoveByIdTest() {
        service.removeById(anyLong());
        verify(repository).removeById(anyLong());
    }

    @Test
    void verifyIsExistsTest() {
        when(repository.isExistsById(1)).thenReturn(true);
        assertTrue(service.isExists(1));
        verify(repository).isExistsById(1);
    }

    @Test
    void verifyIsExistsFailedTest() {
        assertFalse(service.isExists(30));
        verify(repository).isExistsById(30);
    }

    @Test
    void verifyIsExistsByNameTest() {
        when(repository.isExistsByName(0, "Belavia Belarusian Airlines")).thenReturn(true);
        assertTrue(service.isExistsByName(0, "Belavia Belarusian Airlines"));
        verify(repository).isExistsByName(0, "Belavia Belarusian Airlines");
    }

    @Test
    void verifyIsExistsByNameIncorrectIdTest() {
        assertFalse(service.isExistsByName(1, "Belavia Belarusian Airlines"));
        verify(repository).isExistsByName(1, "Belavia Belarusian Airlines");
    }

    @Test
    void verifyIsExistsByNameIncorrectNameTest() {
        assertFalse(service.isExistsByName(0, "Royal Air Maroc"));
        verify(repository).isExistsByName(0, "Royal Air Maroc");
    }

    @Test
    void verifyIsExistsByIataTest() {
        when(repository.isExistsByIata(0, "B2")).thenReturn(true);
        assertTrue(service.isExistsByIata(0, "B2"));
        verify(repository).isExistsByIata(0, "B2");
    }

    @Test
    void verifyIsExistsByIataIncorrectIdTest() {
        assertFalse(service.isExistsByIata(1, "B2"));
        verify(repository).isExistsByIata(1, "B2");
    }

    @Test
    void verifyIsExistsByIataIncorrectIataTest() {
        assertFalse(service.isExistsByIata(0, "AT"));
        verify(repository).isExistsByIata(0, "AT");
    }

    @Test
    void verifyIsExistsByIcaoTest() {
        when(repository.isExistsByIcao(0, "BRU")).thenReturn(true);
        assertTrue(service.isExistsByIcao(0, "BRU"));
        verify(repository).isExistsByIcao(0, "BRU");
    }

    @Test
    void verifyIsExistsByIcaoIncorrectIdTest() {
        assertFalse(service.isExistsByIcao(1, "BRU"));
        verify(repository).isExistsByIcao(1, "BRU");
    }

    @Test
    void verifyIsExistsByIcaoIncorrectIcaoTest() {
        assertFalse(service.isExistsByIcao(0, "RAM"));
        verify(repository).isExistsByIcao(0, "RAM");
    }

    @Test
    void verifyIsExistsByCallsignTest() {
        when(repository.isExistsByCallsign(0, "BELAVIA")).thenReturn(true);
        assertTrue(service.isExistsByCallsign(0, "BELAVIA"));
        verify(repository).isExistsByCallsign(0, "BELAVIA");
    }

    @Test
    void verifyIsExistsByCallsignIncorrectIdTest() {
        assertFalse(service.isExistsByCallsign(1, "BELAVIA"));
        verify(repository).isExistsByCallsign(1, "BELAVIA");
    }

    @Test
    void verifyIsExistsByCallsignIncorrectCallsignTest() {
        assertFalse(service.isExistsByCallsign(0, "ROYAL AIR MAROC"));
        verify(repository).isExistsByCallsign(0, "ROYAL AIR MAROC");
    }

}