package com.topgroup.capa.base.domain.model;

import javax.persistence.Embeddable;

@Embeddable
public class Medida {
	
	private Double largo;

	private Double ancho;

	private Double altura;

	
	public Double getLargo() {
		return largo;
	}

	public void setLargo(Double largo) {
		this.largo = largo;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}
}
