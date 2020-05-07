package com.resliv.stitov.utils;

import com.resliv.stitov.controller.request.SightsRequest;
import com.resliv.stitov.domain.model.City;
import com.resliv.stitov.domain.model.Sights;
import com.resliv.stitov.exceptions.CustomValidation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static Integer validateOffset (String stringOffset){
        int offset;
        try {
            offset = Integer.parseInt(stringOffset);
        } catch (NumberFormatException e){
            throw new CustomValidation("Неправильно указана страница");
        }
        return offset;
    }

    public static Long validatePath (String stringId){
        long id;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e){
            throw new CustomValidation("Неправильно указан путь!");
        }
        return id;
    }

    public static Set<Sights> parseSights(List<SightsRequest> sightsRequestList, City city){
        Set<Sights> sights = new HashSet<>();
        for (SightsRequest sightsRequest : sightsRequestList){
            Sights sight = new Sights();
            sight.setName(sightsRequest.getName());
            sight.setLocation(sightsRequest.getLocation());
            sight.setSightsComment(sightsRequest.getSightsComment());
            sight.setCity(city);
            sights.add(sight);
        }
        return sights;
    }

}
