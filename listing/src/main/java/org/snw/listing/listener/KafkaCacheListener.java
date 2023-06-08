package org.snw.listing.listener;

import org.snw.listing.cache.BaseCache;
import org.snw.location.api.model.LocationModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaCacheListener {

    @Qualifier("locationCache")
    private BaseCache<String, LocationModel> baseCache;

    public KafkaCacheListener(BaseCache baseCache) {
        this.baseCache = baseCache;
    }

    @KafkaListener(topics = "invalid-cache", groupId = "cache-group")
    public void invalidateCache(@Payload String id) {
        // Cache the message or perform any caching logic
        System.out.println("Received message: " + id);

        baseCache.remove(id);
        // Implement your caching logic here
    }
}
