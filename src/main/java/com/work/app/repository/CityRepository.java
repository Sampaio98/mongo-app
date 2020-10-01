package com.work.app.repository;

import com.work.app.domain.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends MongoRepository<City, String> {

    Optional<City> findByName(String name);

    List<City> findByStateInitials(String name);
}
