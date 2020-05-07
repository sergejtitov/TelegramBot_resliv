package com.resliv.stitov.services;

import com.resliv.stitov.domain.model.City;
import com.resliv.stitov.exceptions.EntityAlreadyExists;
import com.resliv.stitov.exceptions.NoSuchEntityException;
import com.resliv.stitov.repository.spring_data.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class CityService {

    private CityRepository cityDao;

    public CityService(CityRepository cityDao) {
        this.cityDao = cityDao;
    }

    public City findCityById(Long cityId){
        Optional<City> searchedCity = cityDao.findById(cityId);
        return searchedCity.orElseThrow(()-> new NoSuchEntityException("В нашей базе такого города нет!"));
    }

    public City findCityByName (String cityName){
        Optional<City> searchedCity = cityDao.findByName(cityName);
        return searchedCity.orElse(null);
    }

    public Page<City> findAll(int limit, int offset){
        return cityDao.findAll(PageRequest.of(offset, limit));
    }

    public void delete (Long cityId){
        Optional<City> deletedCity = cityDao.findById(cityId);
        deletedCity.ifPresent(city -> cityDao.delete(city));
    }

    @Transactional(rollbackFor = Exception.class)
    public City save (City city){
        Optional<City> savedCity = cityDao.findByName(city.getName());
        if (savedCity.isPresent()){
            throw new EntityAlreadyExists("Данный город уже существует!");
        }
        return cityDao.save(city);
    }

    @Transactional(rollbackFor = Exception.class)
    public City update (City city){
        return cityDao.saveAndFlush(city);
    }
}
