package org.snw.location.api.proxy;

import org.snw.location.api.model.LocationModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="location-service", url="localhost:8000/api")
public interface LocationServiceProxy {

	@GetMapping("/location/{id}")
	public LocationModel getLocation
		(@PathVariable String id);
}
