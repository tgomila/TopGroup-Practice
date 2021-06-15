package com.tg.practice.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class Categoria extends Familia{
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private SubFamilia subfamilia;
	
	
	
	//Constructores
	public Categoria() {
		super();
	}


	public Categoria(SubFamilia subfamilia) {
		super();
		this.subfamilia = subfamilia;
	}
	
	
	
	
	//Getters and Setters
	public SubFamilia getSubfamilia() {
		return subfamilia;
	}


	public void setSubfamilia(SubFamilia subfamilia) {
		this.subfamilia = subfamilia;
	}
}
