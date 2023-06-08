package org.snw.location.core.service;

import org.snw.location.api.model.LocationModel;
import org.snw.location.core.event.KafkaCacheEvent;
import org.snw.location.core.exception.NoRecordFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LocationService {

    private static final Map<String, LocationModel> locationMap = new HashMap<>();

    private final KafkaCacheEvent cacheEvent;

    @Autowired
    public LocationService(KafkaCacheEvent cacheEvent) {
        this.cacheEvent = cacheEvent;
    }

    public LocationModel save(String id, String value) {

        LocationModel model = new LocationModel(id, value);
        locationMap.put(id, model);
        cacheEvent.invalidateCachedLocation(model);

        return model;
    }

    public LocationModel getById(String id) {

        if (!locationMap.keySet().contains(id)) {
            throw new NoRecordFoundException();
        }

        return locationMap.get(id);
    }
}
