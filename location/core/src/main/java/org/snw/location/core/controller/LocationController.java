package org.snw.location.core.controller;

import org.snw.location.api.model.LocationModel;
import org.snw.location.core.controller.dto.LocationDto;
import org.snw.location.core.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocationController {


    private final LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping("/location/{id}")
    public ResponseEntity<LocationModel> save(@RequestBody LocationDto dto, @PathVariable String id) {

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LocationModel model  = service.save(id, dto.getValue());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @GetMapping("location/{id}")
    public ResponseEntity<LocationModel> getById(@PathVariable String id) {

        LocationModel model = service.getById(id);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
