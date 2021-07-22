package com.topgroup.capa.base.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Categoria extends Familia {

	@ManyToOne(fetch=FetchType.LAZY)
	private SubFamilia subFamilia;

	
	public SubFamilia getSubFamilia() {
		return subFamilia;
	}

	public void setSubFamilia(SubFamilia subFamilia) {
		this.subFamilia = subFamilia;
	}

	@Override
	public Boolean isSubFamilia() {
		return false;
	}

	@Override
	public Boolean isCategoria() {
		return true;
	}

}
