package homework.spring.controllers;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import homework.spring.logic.Pet;
import homework.spring.logic.Storage;

@RestController
public class PetsController {
	private static final Storage storage = Storage.getStorage();
	private static final AtomicInteger counter = new AtomicInteger(4);

	public PetsController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(value = "/pets", consumes="application/json", produces = "application/json")
	public String addPet(@RequestBody Pet pet) {
		int id = storage.add(pet, counter.getAndIncrement());
		return "Pet created with id = " + id;
	}
	
	@GetMapping(value = "/pets", produces = "application/json")
	public Map<Integer, Pet> getAll(){
		return storage.getAll();
	}
	
	@GetMapping(value = "/pets/{id}", produces = "application/json")
	public Pet getById(@PathVariable int id){
		return storage.getById(id);
	}
	
	@PutMapping(value="/pets/{id}", consumes = "application/json", produces = "application/json")
	public boolean updatePet(@PathVariable int id, @RequestBody Pet pet) {
		return storage.update(id, pet);
	}
	
	@DeleteMapping(value = "/pets/{id}", produces = "application/json")
	public boolean deletePet(@PathVariable int id){
		return storage.delete(id);
	}
}
