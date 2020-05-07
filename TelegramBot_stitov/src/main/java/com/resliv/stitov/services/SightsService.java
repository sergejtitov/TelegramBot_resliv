package com.resliv.stitov.services;

import com.resliv.stitov.domain.model.City;
import com.resliv.stitov.domain.model.Sights;
import com.resliv.stitov.exceptions.NoSuchEntityException;
import com.resliv.stitov.repository.spring_data.CityRepository;
import com.resliv.stitov.repository.spring_data.SightsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class SightsService {

    private SightsRepository sightsDao;
    private CityRepository cityDao;

    public SightsService(SightsRepository sightsDao, CityRepository cityDao) {
        this.sightsDao = sightsDao;
        this.cityDao = cityDao;
    }

    public Sights findSightsById (Long sightsId){
        Optional<Sights> searchedSights = sightsDao.findById(sightsId);
        return searchedSights.orElseThrow(()-> new NoSuchEntityException("В нашей базе такой достопримечательности нет!"));
    }

    public Set<Sights> findSightsByCity(String name){
        Optional<City> searchedCity = cityDao.findByName(name);
        if (searchedCity.isPresent()){
            return searchedCity.get().getSights();
        } else {
            throw new NoSuchEntityException("В нашей базе такого города нет!");
        }
    }

    public void delete(Long sightsId){
        Optional<Sights> deletedSights = sightsDao.findById(sightsId);
        if (deletedSights.isPresent()){
            sightsDao.delete(deletedSights.get());
        } else {
            throw new NoSuchEntityException("Такой достопримечательности нет!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Sights save (Sights sights){
        return sightsDao.save(sights);
    }

    @Transactional(rollbackFor = Exception.class)
    public Sights update(Sights sights){
        return sightsDao.saveAndFlush(sights);
    }
}
