package com.resliv.stitov.repository.spring_data;

import com.resliv.stitov.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long>, JpaRepository<City, Long> {
    Optional<City> findByName (String cityName);
}
