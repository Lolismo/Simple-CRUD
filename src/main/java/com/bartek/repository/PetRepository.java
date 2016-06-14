package com.bartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartek.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	
	
	Pet findPetById(long id);
	List<Pet> findPetByNameIgnoringCase(String petName);
	List<Pet> findPetBySpeciesIgnoringCase(String species);
	
	
}
