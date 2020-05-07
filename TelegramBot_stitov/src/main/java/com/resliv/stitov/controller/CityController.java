package com.resliv.stitov.controller;

import com.resliv.stitov.controller.request.CityRequest;
import com.resliv.stitov.domain.model.City;
import com.resliv.stitov.services.CityService;
import com.resliv.stitov.utils.Utils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping(value = "/cities")
public class CityController {
    public static final int LIMIT = 10;

    private CityService cityService;
    private final ConversionService conversionService;

    public CityController(CityService cityService, ConversionService conversionService) {
        this.cityService = cityService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<Page<City>> getCities(@RequestParam String offsetString) {
        int offset = Utils.validateOffset(offsetString);
        return new ResponseEntity<>(cityService.findAll(LIMIT,offset), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<City> getCityById(@PathVariable String id) {
        Long cityId = Utils.validatePath(id);
        City city = cityService.findCityById(cityId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCity(@PathVariable("id") String id) {
        long cityId = Utils.validatePath(id);
        cityService.delete(cityId);
        return new ResponseEntity<>(cityId, HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<City> createCity(@RequestBody @Valid CityRequest request){
        City city = conversionService.convert(request, City.class);
        return new ResponseEntity<>(cityService.save(Objects.requireNonNull(city)), HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<City> updateCity(@PathVariable("id") String id,
                                           @RequestBody @Valid CityRequest request) {
        long cityId = Utils.validatePath(id);
        City updatedCity = conversionService.convert(request, City.class);
        Objects.requireNonNull(updatedCity).setCityId(cityId);
        return new ResponseEntity<>(cityService.update(updatedCity), HttpStatus.OK);
    }
}
