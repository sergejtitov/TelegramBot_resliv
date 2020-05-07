package com.resliv.stitov.repository.spring_data;

import com.resliv.stitov.domain.model.Sights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface SightsRepository extends CrudRepository<Sights,Long>, JpaRepository<Sights, Long> {

}
