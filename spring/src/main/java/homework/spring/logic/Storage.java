package homework.spring.logic;

import java.util.HashMap;
import java.util.Map;

import homework.spring.exceptions.EntityNotFoundException;

public class Storage {

private static final Storage instance = new Storage();
	
	private final Map<Integer, Pet> pets;
	
	public static Storage getStorage()
	{
		return instance;
	}

	public Storage() {
		// TODO Auto-generated constructor stub
		pets = new HashMap<Integer, Pet>();
		
		pets.put(1, new Pet("Barsik", "Cat", 10));
		pets.put(2, new Pet("Snoop", "Dog", 20));
		pets.put(3, new Pet("Ag1", "Fish", 3));
	}
	
	public int add(Pet pet, int id)
	{
		pets.put(id, pet);
		return id;
	}

	public Map<Integer, Pet> getAll()
	{
		return pets;
	}
	
	public Pet getById(int id) {
		Pet pet = pets.get(id);
		if (pet == null) {
			throw new EntityNotFoundException("Pet with id = " + id + " not found");
		}
		return pet;
	}
	
	public boolean update(int id, Pet updatedPet) {
		Pet pet = pets.get(id);
		if (pet == null) {
			throw new EntityNotFoundException("Pet with id = " + id + " not found");
		}
		pet.setAge(updatedPet.getAge());
		pet.setName(updatedPet.getName());
		pet.setType(updatedPet.getType());
		return true;
	}
	
	public boolean delete(int id) {
		Pet pet = pets.remove(id);
		if (pet == null) {
			throw new EntityNotFoundException("Pet with id = " + id + " not found");
		}
		return true;
	}
}

