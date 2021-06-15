package com.tg.practice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long nroGuia;
	private Date fechaEntrega;
	private Long cantidad;
	
	@Enumerated(EnumType.STRING)
	private EstadoPedido estadoPedido;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Producto producto;
	
	
	//Constructores
	public Pedido() {
		super();
	}
	
	public Pedido(Long id, Long nroGuia, Date fechaEntrega, Long cantidad, EstadoPedido estadoPedido,
			Producto producto) {
		super();
		this.id = id;
		this.nroGuia = nroGuia;
		this.fechaEntrega = fechaEntrega;
		this.cantidad = cantidad;
		this.estadoPedido = estadoPedido;
		this.producto = producto;
	}



	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroGuia() {
		return nroGuia;
	}

	public void setNroGuia(Long nroGuia) {
		this.nroGuia = nroGuia;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
