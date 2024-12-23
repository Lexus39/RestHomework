package homework.spring.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import homework.spring.logic.CompassService;

@RestController
public class CoordinatesController {
	private static final CompassService service = CompassService.getService();

	public CoordinatesController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(value = "/initiate", consumes = "application/json", produces = "application/json")
	public String initiateSides(@RequestBody Map<String,String> sidesCoordinates) {
		service.initiate(sidesCoordinates);
		return "Service is initiated";
	}
	
	@GetMapping(value = "/coordinates/{degree}", produces = "application/json")
	public String getSide(@PathVariable int degree) {
		return service.findSide(degree);
	}
}
