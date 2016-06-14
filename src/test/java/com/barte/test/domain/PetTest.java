package com.barte.test.domain;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.bartek.entity.Pet;
import com.bartek.entity.Visit;
import com.bartek.exception.NotAlivePetException;

public class PetTest {

	private static final LocalDate IMPOSSIBLE_DATE = LocalDate.of(1800, 2, 12);
	private static final LocalDate POSSIBLE_DATE = LocalDate.of(2000, 10, 10);
	private Pet pet;
	
	@Before
	public void setUp() throws Exception {
		
		pet = new Pet();		
	}

	@Test
	public void afterCreationPetCannotBeNull() {
		assertNotNull(pet);
	}
	
	@Test(expected= NotAlivePetException.class)
	public void whenDateOfBirthWhichIsNotPossibleIsTryToSetThenThrowException () throws Exception {
		
		pet.setDateOfBirth(IMPOSSIBLE_DATE);
	}
	
	@Test
	public void whenDateIsPossibleSetDateOfBirthShouldWorksFine() throws Exception {
	
		pet.setDateOfBirth(POSSIBLE_DATE);
		
	}
	
	@Test
	public void stringShouldBeAbleToSetAsDateOfBirth() throws Exception {
		
		pet.setDateOfBirth("2000-05-12");
		
		assertEquals(LocalDate.of(2000, 5, 12), pet.getDateOfBirth());
	}

	@Test 
	public void itsPossibleThatPetCanAddVisit () throws Exception {
		
		pet.getHistoryOfVisits().add(new Visit());
		pet.getHistoryOfVisits().add(new Visit());
		
		assertEquals(2, pet.getHistoryOfVisits().size());
	}
}
