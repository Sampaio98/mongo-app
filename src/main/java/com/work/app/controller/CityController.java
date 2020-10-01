package com.work.app.controller;

import com.work.app.domain.City;
import com.work.app.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService service;

    @PostMapping
    public ResponseEntity<City> saveCity(@Valid @RequestBody City city) {
        City citySaved = service.saveCity(city);
        return ResponseEntity.status(CREATED).body(citySaved);
    }

    @GetMapping("/{name}")
    public ResponseEntity<City> findByName(@PathVariable String name) {
        City city = service.findByName(name);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/state/{name}")
    public ResponseEntity<List<City>> findByState(@PathVariable String name) {
        List<City> cities = service.findByStateInitials(name);
        return ResponseEntity.ok(cities);
    }

}
