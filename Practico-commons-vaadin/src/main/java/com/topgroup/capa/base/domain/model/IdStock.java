package com.topgroup.capa.base.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IdStock implements Serializable{
	
	private static final long serialVersionUID = -9056013506273670427L;

	private Long codigoInterno;
	
	private String token;

	public Long getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(Long codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
