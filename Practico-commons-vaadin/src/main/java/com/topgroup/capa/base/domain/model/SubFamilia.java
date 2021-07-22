package com.topgroup.capa.base.domain.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class SubFamilia extends Familia {
   
	@OneToMany
	@JoinColumn(name="idSubFamilia")
    private Collection<Familia> familias;
    

    
	public Collection<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(Collection<Familia> familias) {
		this.familias = familias;
	}

	@Override
	public Boolean isSubFamilia() {
		return true;
	}

	@Override
	public Boolean isCategoria() {
		return false;
	}

    
}
