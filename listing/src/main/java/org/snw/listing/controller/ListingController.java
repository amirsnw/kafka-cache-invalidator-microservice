package org.snw.listing.controller;

import org.snw.listing.cache.BaseCache;
import org.snw.location.api.model.LocationModel;
import org.snw.location.api.proxy.LocationServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ListingController {

    private LocationServiceProxy proxy;

    @Qualifier("locationCache")
    private BaseCache<String, LocationModel> baseCache;

    @Autowired
    public ListingController(LocationServiceProxy proxy, BaseCache<String, LocationModel> baseCache) {
        this.proxy = proxy;
        this.baseCache = baseCache;
    }


    @GetMapping("/listing/{id}")
    public ResponseEntity<LocationModel> save(@PathVariable String id) {

        LocationModel model = baseCache.get(id);
        if (model == null) {
            model = proxy.getLocation(id);
            baseCache.put(id, model);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
