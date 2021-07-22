package com.topgroup.capa.base.domain.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Maquina {

	@Id
    private Long id;

    private String nombre;
    

    @ManyToMany(mappedBy="maquinas")
    private Collection<Producto> producto;


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


	public Collection<Producto> getProducto() {
		return producto;
	}


	public void setProducto(Collection<Producto> producto) {
		this.producto = producto;
	}
    


 
}
