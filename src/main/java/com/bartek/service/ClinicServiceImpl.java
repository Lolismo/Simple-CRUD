package com.bartek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartek.entity.Pet;
import com.bartek.entity.Visit;
import com.bartek.repository.PetRepository;
import com.bartek.repository.VisitRepository;

@Service
@Transactional
public class ClinicServiceImpl implements ClinicService {

	private PetRepository petRepository;
	private VisitRepository visitRepository;

	@Autowired
	public ClinicServiceImpl(PetRepository petRepository, VisitRepository visitRepository) {
		
		this.petRepository = petRepository;
		this.visitRepository = visitRepository;
	}
	
	@Override
	public Pet findPetById(long id) {
		return petRepository.findPetById(id);
	}

	@Override
	public List<Pet> findPetByNameIgnoringCase(String petName) {
		return petRepository.findPetByNameIgnoringCase(petName);
	}

	@Override
	public List<Pet> findPetBySpeciesIgnoringCase(String species) {
		return petRepository.findPetBySpeciesIgnoringCase(species);
	}

	@Override
	public List<Visit> findVisitsByYearOfVisit(String year) {
		return visitRepository.findVisitsByYearOfVisit(year);
	}

	@Override
	public List<Visit> findVisitsByYearAndMonthOfVisit(String year, String month) {
		return visitRepository.findVisitsByYearAndMonthOfVisit(year, month);
	}

	@Override
	public List<Pet> findAll() {
		return petRepository.findAll();
	}

	@Override
	public void saveAndFlush(Pet p) {
		
		petRepository.saveAndFlush(p);
	}

	@Override
	public void deletePet(Pet p) {
		petRepository.delete(p);
		
	}

}
