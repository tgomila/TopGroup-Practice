package com.topgroup.capa.base.view.bean;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.commons.vaadin.view.bean.ViewBean;

public class ProductoViewBean implements ViewBean{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcion;
	private String tipoProducto;
	
	public ProductoViewBean() {}
	
	public ProductoViewBean(String codigo, String descripcion, String tipoProducto) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipoProducto = tipoProducto;
	}	
	
	//Inicializarlo mediante un Producto.java, puede que para ser un "view" no sea bueno. Para un futuro chequear si
	//	este constructor corresponde o no.
	public ProductoViewBean(Producto p) {
		super();
		codigo = p.getCodigo();
		descripcion = p.getDescripcion();
		tipoProducto = p.getTipoProducto().getDescripcion();
	}
	@Override
	public void initialize() {
		this.codigo = null;
		this.descripcion = null;
		this.tipoProducto = null;
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

	
	
	
}
