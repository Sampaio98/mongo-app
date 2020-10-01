package com.work.app.service;

import com.work.app.domain.City;
import com.work.app.exception.IntegrityViolationException;
import com.work.app.exception.LocaleIntegrationException;
import com.work.app.exception.ObjectNotFoundException;
import com.work.app.repository.CityRepository;
import com.work.app.service.integration.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Autowired
    private LocaleService localeService;

    public City saveCity(City city) {
        validateCityNameOnDb(city.getName());
        verifyIfCityExists(city);
        return repository.save(city);
    }

    public City findByName(String name) {
        return repository.findByName(name.toUpperCase()).orElseThrow(() -> new ObjectNotFoundException("city"));
    }

    public List<City> findByStateInitials(String name) {
        return repository.findByStateInitials(name.toUpperCase());
    }

    private void validateCityNameOnDb(String name) {
        Optional<City> city = repository.findByName(name);
        if (city.isPresent()) {
            throw new IntegrityViolationException(name + " is already registered on our database");
        }
    }

    private void verifyIfCityExists(City city) {
        City cityExternalAPI = localeService.findByName(city.getName());

        if (!cityExternalAPI.getState().getInitials().equalsIgnoreCase(city.getState().getInitials())) {
            throw new LocaleIntegrationException("City is not from that state!");
        }
    }
}
