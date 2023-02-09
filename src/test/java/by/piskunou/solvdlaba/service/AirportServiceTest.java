package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.AirportRepository;
import by.piskunou.solvdlaba.service.impl.AirportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Mock
    private CityService cityService;

    @Mock
    private AirportRepository repository;

    @InjectMocks
    private AirportServiceImpl airportService;

    @Test
    void verifyFindAllTest() {
        airportService.findAll();
        verify(repository).findAll();
    }

    @Test
    void verifyFindByIdTest() {
        Airport airport = Airport.builder()
                                 .id(1L)
                                 .name("Minsk National Airport")
                                 .iata("MSQ")
                                 .icao("UMMS")
                                 .build();
        when(repository.findById(1)).thenReturn(Optional.of(airport));
        assertEquals(airport, airportService.findById(1));
        verify(repository).findById(1);
    }

    @Test
    void verifyFindByIdFailedTest() {
        assertThrows(ResourceNotExistsException.class, () -> airportService.findById(Long.MAX_VALUE));
        verify(repository).findById(Long.MAX_VALUE);
    }

    @Test
    void verifySearchTest() {
        Airport airport = Airport.builder()
                                 .name("Mi")
                                 .iata("S")
                                 .build();

        airportService.search(airport);
        assertEquals("%Mi%", airport.getName());
        assertEquals("%S%", airport.getIata());
        assertEquals("%%", airport.getIcao());
        verify(repository).search(airport);
    }

    @Test
    void verifyCreateTest() {
        Airport airport = Airport.builder()
                                 .name("Agadir – Al Massira Airport")
                                 .iata("AGA")
                                 .icao("GMAD")
                                 .build();
        when(cityService.isExists(85)).thenReturn(true);

        assertEquals(airport, airportService.create(85, airport));
        verify(cityService).isExists(85);
        verify(repository).isExistsByName(0, "Agadir – Al Massira Airport");
        verify(repository).isExistsByIata(0, "AGA");
        verify(repository).isExistsByIcao(0, "GMAD");
        verify(repository).create(85, airport);
    }

    @Test
    void verifyCreateIncorrectCityIdTest() {
        assertThrows(ResourceNotExistsException.class, () -> airportService.create(65, any()));
        verify(cityService).isExists(65);
    }

    @Test
    void verifyCreateExistedNameTest() {
        Airport airport = Airport.builder()
                                 .name("Minsk National Airport")
                                 .build();
        when(cityService.isExists(85)).thenReturn(true);
        when(airportService.isExistsByName(0, "Minsk National Airport")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> airportService.create(85, airport));
        verify(cityService).isExists(85);
        verify(repository).isExistsByName(0, "Minsk National Airport");
    }

    @Test
    void verifyCreateExistedIataTest() {
        Airport airport = Airport.builder()
                                 .name("Agadir – Al Massira Airport")
                                 .iata("MSQ")
                                 .build();
        when(cityService.isExists(85)).thenReturn(true);
        when(airportService.isExistsByIata(0, "MSQ")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> airportService.create(85, airport));
        verify(cityService).isExists(85);
        verify(repository).isExistsByName(0, "Agadir – Al Massira Airport");
        verify(repository).isExistsByIata(0, "MSQ");
    }

    @Test
    void verifyCreateExistedIcaoTest() {
        Airport airport = Airport.builder()
                                 .name("Agadir – Al Massira Airport")
                                 .iata("AGA")
                                 .icao("UMMS")
                                 .build();
        when(cityService.isExists(85)).thenReturn(true);
        when(airportService.isExistsByIcao(0, "UMMS")).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> airportService.create(85, airport));
        verify(cityService).isExists(85);
        verify(repository).isExistsByName(0, "Agadir – Al Massira Airport");
        verify(repository).isExistsByIata(0, "AGA");
        verify(repository).isExistsByIcao(0, "UMMS");
    }

    @Test
    void verifyUpdateByIdTest() {
        Airport airport = Airport.builder()
                                 .name("Agadir – Al Massira Airport")
                                 .iata("AGA")
                                 .icao("GMAD")
                                 .build();
        when(airportService.isExists(43)).thenReturn(true);
        when(cityService.isExists(85)).thenReturn(true);

        assertEquals(airport, airportService.updateById(43, 85, airport));
        verify(repository).isExistsById(43);
        assertEquals(43, airport.getId());
        verify(repository).update(85, airport);
    }

    @Test
    void verifyUpdateByNonexistentIdTest() {
        Airport airport = Airport.builder()
                                 .name("Agadir – Al Massira Airport")
                                 .iata("AGA")
                                 .icao("GMAD")
                                 .build();
        when(cityService.isExists(85)).thenReturn(true);

        assertEquals(airport, airportService.updateById(Long.MAX_VALUE, 85, airport));
        verify(repository).isExistsById(Long.MAX_VALUE);
    }

    @Test
    void verifyRemoveByIdTest() {
        airportService.removeById(1);
        verify(repository).removeById(1);
    }

    @Test
    void verifyIsExistsTest() {
        airportService.isExists(1);
        verify(repository).isExistsById(1);
    }

    @Test
    void verifyIsExistsFailedTest() {
        airportService.isExists(Long.MAX_VALUE);
        verify(repository).isExistsById(Long.MAX_VALUE);
    }

    @Test
    void verifyIsExistsByNameTest() {
        airportService.isExistsByName(0, "Minsk National Airport");
        verify(repository).isExistsByName(0, "Minsk National Airport");
    }

    @Test
    void verifyIsExistsByIataTest() {
        airportService.isExistsByIata(0, "MSQ");
        verify(repository).isExistsByIata(0, "MSQ");
    }

    @Test
    void verifyIsExistsByIcaoTest() {
        airportService.isExistsByIcao(0, "UMMS");
        verify(repository).isExistsByIcao(0, "UMMS");
    }

    //TODO Спытаць пра адмоўныя галіны

}