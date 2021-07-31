package com.topgroup.capa.base.view.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.commons.vaadin.view.bean.ViewBean;

@Component
public class ProductoViewBean implements ViewBean{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcion;
	private String tipoProducto;
	private Short productosPorPaquete;
	private Date fechaAlta;
	
	public ProductoViewBean() {}
	
	public ProductoViewBean(String codigo, String descripcion, String tipoProducto, 
			Short productosPorPaquete, Date fechaAlta) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipoProducto = tipoProducto;
		this.productosPorPaquete = productosPorPaquete; 
		this.fechaAlta = fechaAlta;
	}	
	
	//Inicializarlo mediante un Producto.java, puede que para ser un "view" no sea bueno. Para un futuro chequear si
	//	este constructor corresponde o no.
	public ProductoViewBean(Producto p) {
		super();
		codigo = p.getCodigo();
		descripcion = p.getDescripcion();
		tipoProducto = p.getTipoProducto().getDescripcion();
		productosPorPaquete = p.getProductosPorPaquete();
		fechaAlta = p.getFechaAlta();
	}
	@Override
	public void initialize() {
		/*this.codigo = null;
		this.descripcion = null;
		this.tipoProducto = null;
		this.productosPorPaquete = null;*/
	}
	
	
	//Getters and Setters
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Short getProductosPorPaquete() {
		return productosPorPaquete;
	}

	public void setProductosPorPaquete(Short productosPorPaquete) {
		this.productosPorPaquete = productosPorPaquete;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
	
	
}
