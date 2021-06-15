package com.tg.practice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Stock {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoInterno;
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String token;
	
	private Date fechaStock;
	private Long cantidad;
	private String nota;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Producto producto;
	
	
	//Constructores
	public Stock() {
		super();
	}
	
	
	public Stock(Long id, Long codigoInterno, String token, Date fechaStock, Long cantidad, String nota, Producto producto) {
		super();
		this.id = id;
		this.codigoInterno = codigoInterno;
		this.token = token;
		this.fechaStock = fechaStock;
		this.cantidad = cantidad;
		this.nota = nota;
		this.producto = producto;
	}
	
	
	//Getters and Setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
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
	public Date getFechaStock() {
		return fechaStock;
	}
	public void setFechaStock(Date fechaStock) {
		this.fechaStock = fechaStock;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
