package com.bartek.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "visit")
public class Visit {
	
	
	@Id
	@Column(name = "visit_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "date_of_visit")
	@NotBlank
	private LocalDateTime dateOfVisit;

	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	public LocalDateTime getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(LocalDateTime dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public long getId() {
		return id;
	}
	
	


}
