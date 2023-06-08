package org.snw.listing;

import org.snw.listing.cache.BaseCache;
import org.snw.listing.cache.BaseCacheImpl;
import org.snw.location.api.model.LocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.snw.location.api.proxy", "org.snw.listing"})
@PropertySource(value = "classpath:config.properties")
public class ListingApplication {

	@Autowired
	Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ListingApplication.class, args);
	}

	@Bean("locationCache")
	public BaseCache roomCacheBean() {
		return (BaseCache) new BaseCacheImpl<String, LocationModel>(Long.valueOf(getProperty("cache.config.location.timeToLive")),
				Long.parseLong(getProperty("cache.config.location.timerInterval")));
	}

	private String getProperty(String propertyPath) {
		return Objects.requireNonNull(env.getProperty(propertyPath),
				"env: " + propertyPath + " not provided!");
	}
}
