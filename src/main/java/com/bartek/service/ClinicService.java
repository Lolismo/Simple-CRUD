package com.bartek.service;

import java.util.List;

import com.bartek.entity.Pet;
import com.bartek.entity.Visit;

public interface ClinicService {

	Pet findPetById(long id);
	List<Pet> findPetByNameIgnoringCase(String petName);
	List<Pet> findPetBySpeciesIgnoringCase(String species);
	List<Visit> findVisitsByYearOfVisit(String year);
	List<Visit> findVisitsByYearAndMonthOfVisit(String year, String month);
	List<Pet> findAll();
	void saveAndFlush(Pet p);
	void deletePet(Pet p);
}
