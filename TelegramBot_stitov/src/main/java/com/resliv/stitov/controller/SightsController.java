package com.resliv.stitov.controller;

import com.resliv.stitov.controller.request.SightsRequest;
import com.resliv.stitov.domain.model.Sights;
import com.resliv.stitov.services.SightsService;
import com.resliv.stitov.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.Objects;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/sights")
public class SightsController {

    private SightsService sightsService;
    private final ConversionService conversionService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sights> getSightsById(@PathVariable String id) {
        Long sightsId = Utils.validatePath(id);
        Sights sights = sightsService.findSightsById(sightsId);
        return new ResponseEntity<>(sights, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteSights(@PathVariable("id") String id) {
        long sightsId = Utils.validatePath(id);
        sightsService.delete(sightsId);
        return new ResponseEntity<>(sightsId, HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sights> createSights(@RequestBody @Valid SightsRequest request){
        Sights sights = conversionService.convert(request, Sights.class);
        return new ResponseEntity<>(sightsService.save(Objects.requireNonNull(sights)), HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Sights> updateSights(@PathVariable("id") String id,
                                               @RequestBody @Valid SightsRequest request) {
        long sightsId = Utils.validatePath(id);
        Sights updatedSights = conversionService.convert(request, Sights.class);
        Objects.requireNonNull(updatedSights).setSightsId(sightsId);
        return new ResponseEntity<>(sightsService.update(updatedSights), HttpStatus.OK);
    }

}
