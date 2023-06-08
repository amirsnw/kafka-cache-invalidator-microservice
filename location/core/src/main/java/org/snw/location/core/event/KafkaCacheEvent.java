package org.snw.location.core.event;

import org.snw.location.api.model.LocationModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaCacheEvent {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaCacheEvent(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void invalidateCachedLocation(LocationModel location) {
        kafkaTemplate.send("invalid-cache", location.getId(), location.getId());
    }
}
