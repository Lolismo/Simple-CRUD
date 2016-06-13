package com.bartek.controllerTest;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bartek.builder.PetBuilder;
import com.bartek.controller.PetController;
import com.bartek.entity.Pet;
import com.bartek.service.ClinicService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PetControllerTest {

	private MockMvc mockMvc;
		
	private ClinicService clinicService = Mockito.mock(ClinicService.class);
	
	private Pet one;
	private Pet two;
	private Pet toAdd;
	
	@Before
	public void setUp () throws Exception {
	
		mockMvc = MockMvcBuilders.standaloneSetup(new PetController(clinicService)).build();
			
		one = new PetBuilder()
				.id(1L)
				.dateOfBirth("2000-10-12")
				.name("Kajtek")
				.species("dog")
				.weight(2000)
				.build();
		
		two = new PetBuilder()
				.id(2L)
				.dateOfBirth("2010-04-20")
				.name("Kotek")
				.species("cat")
				.weight(1500)
				.build();

		toAdd = new PetBuilder()
				.id(10)
				.dateOfBirth("2000-01-01")
				.name("TestPet")
				.species("gorilla")
				.weight(150000L)
				.build();
	
	
	
	}
	
	
	@Test
	public void showAllPetsShouldAddAllPetsToModelAndRenderCorrectView()  throws Exception{
		
	when(clinicService.findAll()).thenReturn(Arrays.asList(one,two));
	
	mockMvc.perform(get("/pet/getAll"))
				.andExpect(status().isOk())
				.andExpect(view().name("allPets"))
				.andExpect(model().attribute("allPets", hasSize(2)))
				.andExpect(model().attribute("allPets", hasItem(
						allOf(
								hasProperty("id", is(1L)),
								hasProperty("dateOfBirth", is(LocalDate.of(2000, 10, 12))),
								hasProperty("name", is("Kajtek")),
								hasProperty("species", is("dog")),
								hasProperty("weight", is(2000L))
								
						
						)
						)))
				.andExpect(model().attribute("allPets", hasItem(
						allOf(
								hasProperty("id", is(2L)),
								hasProperty("dateOfBirth", is(LocalDate.of(2010, 4, 20))),
								hasProperty("name", is("Kotek")),
								hasProperty("species", is("cat")),
								hasProperty("weight", is(1500L))
								
								
								)
						)));
	
	verify(clinicService,times(1)).findAll();
	verifyNoMoreInteractions(clinicService);

				
	}


	@Test
	public void itReturnsPetFoundByItName () throws Exception {
	
		when(clinicService.findPetByNameIgnoringCase("kajtek")).thenReturn(Arrays.asList(one));
		
		mockMvc.perform(get("/pet/findByName/{petName}", "kajtek"))
		.andExpect(status().isOk())
		.andExpect(view().name("findByName"))
		.andExpect(model().attribute("foundPets", hasSize(1)))
		.andExpect(model().attribute("foundPets", hasItem(
				allOf(
						hasProperty("id", is(1L)),
						hasProperty("dateOfBirth", is(LocalDate.of(2000, 10, 12))),
						hasProperty("name", is("Kajtek")),
						hasProperty("species", is("dog")),
						hasProperty("weight", is(2000L))
						
				
				)
				)));

	
		verify(clinicService,times(1)).findPetByNameIgnoringCase("kajtek");
		verifyNoMoreInteractions(clinicService);

		
		
	}

	@Test
	public void itReturnsPetFoundByItId () throws Exception {
	
		when(clinicService.findPetById(2L)).thenReturn(two);
		
		mockMvc.perform(get("/pet/findById/{id}", 2L))
		.andExpect(status().isOk())
		.andExpect(view().name("findById"))
		.andExpect(model().attribute("foundPet", hasProperty("id", is(2L))))
        .andExpect(model().attribute("foundPet", hasProperty("dateOfBirth", is(LocalDate.of(2010, 4, 20)))))
        .andExpect(model().attribute("foundPet", hasProperty("name", is("Kotek"))))
		.andExpect(model().attribute("foundPet", hasProperty("species", is("cat"))))
		.andExpect(model().attribute("foundPet", hasProperty("weight", is(1500L))));
		
		
		verify(clinicService,times(1)).findPetById(2L);
		verifyNoMoreInteractions(clinicService);

	}
	
	@Test
	public void petShouldBeAddAndRedirectToUrlWithItselfId() throws Exception{
		
		mockMvc.perform(post("/pet/add")
				.param("id", "10")
				.param("species", "gorilla")
				.param("name", "gorylek"))
		.andExpect(redirectedUrl("/pet/findById/10"))
		.andExpect(model().attribute("petToAdd", hasProperty("name", is("gorylek"))));
		
						
		ArgumentCaptor<Pet> formObjectArgument = ArgumentCaptor.forClass(Pet.class);
		
		verify(clinicService, times(1)).saveAndFlush(formObjectArgument.capture());
		verifyNoMoreInteractions(clinicService);
		
		Pet petFromForm = formObjectArgument.getValue();
		assertEquals(10, petFromForm.getId());
		assertEquals("gorilla", petFromForm.getSpecies());	
		
	}
	
	@Test
	public void showAddPetForm() throws Exception {
		
		mockMvc.perform(get("/pet/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("addNewPet"));
		
	}
	
	@Ignore
	public void updateMethodCanChangeParameters() throws Exception{
		
	when(clinicService.findPetById(1)).thenReturn(one);
		
		
	 mockMvc.perform(post("/pet/update/{id}",clinicService.findPetById(1).getId())
		.param("species", "gorilla"))
	    .andExpect(status().isFound())
	 	.andExpect(redirectedUrl("/pet/findById/1"));
	 
	 verify(clinicService, times(1)).saveAndFlush(any());
	 verifyNoMoreInteractions(clinicService);
	 
	 assertThat(one.getId(), is (1L));
	 assertThat(one.getSpecies(), is("gorilla"));
	 assertThat(one.getName(), is("Kajtek"));
	}
	
	@Test
	public void deletePetRemovingPet() throws Exception {
	
		when(clinicService.findAll()).thenReturn(Arrays.asList(one,two));
		when(clinicService.findPetById(1)).thenReturn(one);
		
		mockMvc.perform(get("/pet/delete/{id}", 1))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/pet/getAll"));
		
		
		verify(clinicService ,times(1)).deletePet(any());
		
	}
}
