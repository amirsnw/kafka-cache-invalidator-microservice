package org.snw.location.core;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class LocationCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationCoreApplication.class, args);
	}

	@Bean
	@Order(-1)
	public NewTopic createNewTopic() {
		return TopicBuilder.name("invalid-cache").build();
	}

}
