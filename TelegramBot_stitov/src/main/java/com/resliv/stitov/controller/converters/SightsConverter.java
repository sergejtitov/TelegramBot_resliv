package com.resliv.stitov.controller.converters;

import com.resliv.stitov.controller.request.SightsRequest;
import com.resliv.stitov.domain.model.City;
import com.resliv.stitov.domain.model.Sights;
import com.resliv.stitov.exceptions.NoSuchEntityException;
import com.resliv.stitov.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SightsConverter implements Converter<SightsRequest, Sights> {

    private CityService cityService;

    @Override
    public Sights convert(SightsRequest sightsRequest) {
        Sights sights = new Sights();
        City city = cityService.findCityByName(sightsRequest.getCityName().toUpperCase());
        if (city == null){
            throw new NoSuchEntityException("Такого города нет!");
        }
        sights.setCity(city);
        sights.setName(sightsRequest.getName());
        sights.setLocation(sightsRequest.getLocation());
        sights.setSightsComment(sightsRequest.getSightsComment());
        return sights;
    }
}
