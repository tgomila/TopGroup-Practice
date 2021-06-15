package com.tg.practice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
//@Table(name ="subfamilia")
public class SubFamilia extends Familia{
	
	//orphan removal elimina de la BD cuando subfamilia no lo referencie mas
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	List<Familia> familias;
	
	
	//Constructores
	public SubFamilia() {
		super();
		familias = new ArrayList<Familia>();
	}
	
	public SubFamilia(List<Familia> familias) {
		super();
		this.familias = familias;
	}
	
	
	//Getters and Setters
	public List<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}
}
