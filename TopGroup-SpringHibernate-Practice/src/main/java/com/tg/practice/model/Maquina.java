package com.tg.practice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Maquina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@ManyToMany(mappedBy="maquinas")
	private List<Producto> productos;
	
	
	//Constructores
	public Maquina() {
		super();
		productos = new ArrayList<Producto>();
	}

	public Maquina(Long id, List<Producto> productos) {
		super();
		this.id = id;
		this.productos = productos;
	}
	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void agregarProducto(Producto producto) {
		this.productos.add(producto);
	}
	
}
