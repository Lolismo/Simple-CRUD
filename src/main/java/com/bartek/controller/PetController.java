package com.bartek.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bartek.entity.Pet;
import com.bartek.service.ClinicService;

@Controller
@RequestMapping("/pet")
public class PetController {

	private ClinicService clinicService;

	@Autowired
	public PetController(ClinicService petService) {

		this.clinicService = petService;
	}

//  <------------- GET ALL ------------>	
	
	@RequestMapping("/getAll")
	public String findAllPets(Model model) {

		List<Pet> allPetsFromDatabase = clinicService.findAll();
		model.addAttribute("allPets", allPetsFromDatabase);

		return "home";
	}

//  <------------- FIND ------------>
	
	@RequestMapping("/findByName/{petName}")
	public String findPetByName(@PathVariable String petName, Model model) {

		List<Pet> foundedPets = clinicService.findPetByNameIgnoringCase(petName);
		model.addAttribute("foundPets", foundedPets);

		return "findByName";
	}

	@RequestMapping("/findById/{id}")
	public String findPetById(@PathVariable int id, Model model){
		
		Pet foundedPet = clinicService.findPetById(id);
		model.addAttribute("foundPet", foundedPet);
		model.addAttribute("visits", foundedPet.getHistoryOfVisits());
		
		return"findById";
	}
	
	//  <------------- ADD ------------>

	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addNewPetForm(Model model){
		
		Pet petForm = new Pet();
		model.addAttribute("petToAdd", petForm);
			
		return "addNewPet";
	}
	
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute ("petToAdd") Pet pet, 
			BindingResult result, Model model ){
		model.addAttribute("idOfPet", pet.getId());
		
		if(result.hasErrors()){
			return "pet/add";
		}
		
		clinicService.saveAndFlush(pet);
		
		return "redirect:/pet/findById/{idOfPet}";
	}
	
//  <------------- UPDATE ------------>
	@RequestMapping (value ="/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model){
		
		Pet toUpdate = clinicService.findPetById(id);
		
		model.addAttribute("petToUpdate", toUpdate);
		
		return "updatePet";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update (@Valid @ModelAttribute ("petToUpdate") Pet pet,
						  BindingResult result ) {
		if(result.hasErrors())
			return "pet/update/{id}";
		
		clinicService.saveAndFlush(pet);
		
		return "redirect:/pet/findById/{id}";
		
	}
	
//  <------------- DELETE ------------>
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete (@PathVariable int id){
		
		
		clinicService.deletePet(clinicService.findPetById(id));
		
		return"redirect:/pet/getAll";
		
	}
	
}