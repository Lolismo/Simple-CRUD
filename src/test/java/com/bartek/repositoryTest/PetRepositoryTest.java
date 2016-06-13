package com.bartek.repositoryTest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.*;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.bartek.builder.PetBuilder;
import com.bartek.entity.Pet;
import com.bartek.entity.Visit;
import com.bartek.repository.PetRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup(value = "example-data.xml")
@DirtiesContext
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "example-data.xml" })
public class PetRepositoryTest {

	@Autowired
	private PetRepository petRepository;

	private static final String PET_NAME = "kajtek";
	private static final String SPECIES = "cat";
	private static final int ID_ONE = 1;
	private Pet[] pet;
	
	@Before
	public void setUp () throws Exception {
		
		pet = new Pet[2];
		
		pet[0] = new PetBuilder()
				.id(10)
				.name("testName")
				.species("testSpecies")
				.build();
		
		pet[1] = new PetBuilder()
				.id(12)
				.name("testName")
				.species("testSpecies")
				.build();
	}
	
	@Test
	public void shouldFindAllPetsInDatabase() {

		assertEquals(2, petRepository.findAll().size());
	}

	@Test
	public void petRepositoryReturnPetByItNameIgnoringCase (){
		
		assertEquals(1, petRepository.findPetByNameIgnoringCase(PET_NAME).size());
	}

	@Test
	public void itShouldBeAbleToFindPetById() {
		
		assertEquals(ID_ONE, petRepository.findPetById(ID_ONE).getId());
	}

	@Test
	public void petShouldReturnCountOfItselfVisitis() {

		assertEquals(4, petRepository.findPetById(ID_ONE).getHistoryOfVisits().size());
	}

	
	@Test
	public void petRepositoryShouldFindPetBySpeciesIgnoringCase () {
		
		assertEquals(1, petRepository.findPetBySpeciesIgnoringCase(SPECIES).size());		
	}
	
	@Test
	public void updateMethodUpdatingData () throws Exception {

		Pet foundPet = petRepository.findPetById(1);
		
		foundPet.setSpecies("gorilla");
		foundPet.setName("testName");
		
		petRepository.saveAndFlush(foundPet);
		
		assertThat(petRepository.findPetById(1).getName(), is("testName"));
		assertThat(petRepository.findPetById(1).getSpecies(), is("gorilla"));
	}
	
	@Test
	public void savePetAddPetIntoDatabase() throws Exception {
			
		petRepository.saveAndFlush(pet[0]);
		petRepository.saveAndFlush(pet[1]);
		
		assertThat(petRepository.count(), is(4L));
		
	}
	
	@Test
	public void deleteMethodRemovingData() throws Exception {
		
		petRepository.delete(petRepository.findPetById(ID_ONE));
		
		assertThat(petRepository.findAll().size(), is(1));
		assertNull(petRepository.findPetById(ID_ONE));
		
	}
	
	@Test
	public void itsPossibleToAddNewVisitToPet () throws Exception {
		
		Pet checkItVisits = petRepository.findPetById(1L);
		
		checkItVisits.getHistoryOfVisits().add(new Visit());
		
		assertThat(checkItVisits.getHistoryOfVisits().size(), is(5));
		
	}
}
