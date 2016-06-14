package com.bartek.builder;

import com.bartek.entity.Pet;

public class PetBuilder {

	private Pet pet;

	public PetBuilder() {
		pet = new Pet();
	}

	public PetBuilder id(long id) {
		
		pet.setId(id);
		
		return this;
	}

	public PetBuilder dateOfBirth(String dateOfBirth) throws Exception {

		pet.setDateOfBirth(dateOfBirth);

		return this;
	}

	public PetBuilder species(String species) {

		pet.setSpecies(species);
		return this;
	}

	public PetBuilder weight(long weight) {

		pet.setWeight(weight);
		return this;
	}

	public PetBuilder name(String name) {

		pet.setName(name);
		return this;
	}

	public Pet build() {

		return pet;
	}
}
