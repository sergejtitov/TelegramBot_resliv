package com.resliv.stitov.controller.converters;

import com.resliv.stitov.controller.request.CityRequest;
import com.resliv.stitov.domain.model.City;
import com.resliv.stitov.utils.Utils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityConverter implements Converter<CityRequest, City> {

    @Override
    public City convert(CityRequest cityRequest) {
        City city = new City();
        city.setName(cityRequest.getName().toUpperCase());
        city.setCountry(cityRequest.getCountry());
        city.setComment(cityRequest.getComment());
        city.setSights(Utils.parseSights(cityRequest.getSights(),city));
        return city;
    }
}
