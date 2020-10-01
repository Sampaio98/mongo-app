package com.work.app.service;

import com.work.app.domain.City;
import com.work.app.exception.IntegrityViolationException;
import com.work.app.exception.LocaleIntegrationException;
import com.work.app.exception.ObjectNotFoundException;
import com.work.app.repository.CityRepository;
import com.work.app.service.integration.LocaleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.work.app.mock.MockFactory.mockCity;
import static com.work.app.mock.MockFactory.mockCity2;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @InjectMocks
    private CityService service;

    @Mock
    private CityRepository repository;

    @Mock
    private LocaleService localeService;

    @Test
    public void shouldSaveCity() {
        when(repository.findByName(any())).thenReturn(Optional.empty());
        when(localeService.findByName(any())).thenReturn(mockCity());
        when(repository.save(any())).thenReturn(mockCity());

        City city = service.saveCity(mockCity());

        assertNotNull("City not saved!", city);

        verify(repository).findByName("LONDRINA");
        verify(localeService).findByName("LONDRINA");
        verify(repository).save(any());
    }

    @Test(expected = IntegrityViolationException.class)
    public void shouldNotSaveSameCity() {
        when(repository.findByName(any())).thenReturn(Optional.of(mockCity()));
        service.saveCity(mockCity());
    }

    @Test(expected = LocaleIntegrationException.class)
    public void shouldNotSaveCityFromWrongState() {
        when(repository.findByName(any())).thenReturn(Optional.empty());
        when(localeService.findByName(any())).thenReturn(mockCity2());
        service.saveCity(mockCity());
    }

    @Test
    public void shouldFindCityByName() {
        when(repository.findByName(any())).thenReturn(Optional.of(mockCity()));

        City city = service.findByName("Londrina");

        assertNotNull("City not found!", city);
        verify(repository).findByName("LONDRINA");
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldNotFindCityByName() {
        when(repository.findByName(any())).thenReturn(Optional.empty());
        service.findByName("Londrina");
    }

    @Test
    public void shouldFindCityByStateName() {
        when(repository.findByStateInitials(any())).thenReturn(Collections.singletonList(mockCity()));
        List<City> cities = service.findByStateInitials("PR");

        assertNotEquals("No city was found", new ArrayList<>(), cities);
        verify(repository).findByStateInitials("PR");
    }

}