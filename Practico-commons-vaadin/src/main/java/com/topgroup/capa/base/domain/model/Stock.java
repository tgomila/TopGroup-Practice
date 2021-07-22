package com.topgroup.capa.base.domain.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Stock {

	@EmbeddedId
	private IdStock id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaStock;

	private Long cantidad;

	private String nota;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProducto")
	private Producto producto;

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Date getFechaStock() {
		return fechaStock;
	}

	public void setFechaStock(Date fechaStock) {
		this.fechaStock = fechaStock;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

}
