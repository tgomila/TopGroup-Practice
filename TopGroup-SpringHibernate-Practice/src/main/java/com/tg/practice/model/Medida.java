package com.tg.practice.model;

import javax.persistence.Embeddable;

@Embeddable
public class Medida {
	
	private Double largo;
	private Double ancho;
	private Double altura;
	
	public Medida(Double largo, Double ancho, Double altura) {
		super();
		this.largo = largo;
		this.ancho = ancho;
		this.altura = altura;
	}
	
	public Medida() {
		super();
	}

	//Getters and Setters
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
