package com.bartek.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bartek.exception.NotAlivePetException;

@Entity
public class Pet {

	private static final String ERROR_MESSAGE = "Your pet is probably death already !";
	private static final LocalDate NONE_PET_IS_OLDER_THAN_THIS = LocalDate.of(1960, 1, 1);

	@Id
	@Column(name = "pet_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "species")
	private String species;

	@Column(name = "weight_g")
	private long weight;

	@Column(name = "name")
	private String name;
	
	
	//cascade = CascadeType.ALL without this it's impossible to remove Pet object
	@OneToMany(mappedBy = "pet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Visit> historyOfVisits = new ArrayList<>();

	public Pet() {

	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Visit> getHistoryOfVisits() {
		return historyOfVisits;
	}

	public long getId() {
		return id;
	}

	// ONLY FOR TEST !!!
	public void setId(long id) {

		this.id = id;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) throws NotAlivePetException {
		if (dateOfBirth.isBefore(NONE_PET_IS_OLDER_THAN_THIS))
			throw new NotAlivePetException(ERROR_MESSAGE);

		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfBirth(String string) throws NotAlivePetException {

		this.setDateOfBirth(LocalDate.parse(string));

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (historyOfVisits == null) {
			if (other.historyOfVisits != null)
				return false;
		} else if (!historyOfVisits.equals(other.historyOfVisits))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (species == null) {
			if (other.species != null)
				return false;
		} else if (!species.equals(other.species))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

}
