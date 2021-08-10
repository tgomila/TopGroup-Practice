package com.topgroup.capa.base.view.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.topgroup.commons.vaadin.annotation.DateFieldFormat;
import com.topgroup.commons.vaadin.annotation.FieldForm;
import com.topgroup.commons.vaadin.view.bean.ViewBean;

@Component
public class ProductoViewBean implements ViewBean{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcion;
	private TipoProducto tipoProducto;
	private Short productosPorPaquete;
	private Date fechaAlta;
	
	public ProductoViewBean() {}
	
	public ProductoViewBean(String codigo, String descripcion, TipoProducto tipoProducto, 
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
		fechaAlta = p.getFechaAlta();
		productosPorPaquete = p.getProductosPorPaquete();		
		tipoProducto = p.getTipoProducto();
	}
	public ProductoViewBean(ProductoFilter p) {
		codigo = p.getCodigo();
		descripcion = p.getDescripcion();
		fechaAlta = p.getFechaAlta();
		productosPorPaquete = p.getProductosPorPaquete();		
		tipoProducto = p.getTipoProducto();		
	}
	@Override
	public void initialize() {
		/*this.codigo = null;
		this.descripcion = null;
		this.tipoProducto = null;
		this.productosPorPaquete = null;*/
	}
	
	
	//Getters and Setters
	@FieldForm(messageKey = "ProductoViewBean.filter.codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@FieldForm(messageKey = "ProductoViewBean.filter.descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@FieldForm(messageKey = "ProductoViewBean.filter.tipoProducto")
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
	@FieldForm(messageKey = "ProductoViewBean.filter.productosPorPaquete")
	public Short getProductosPorPaquete() {
		return productosPorPaquete;
	}
		
	public void setProductosPorPaquete(Short productosPorPaquete) {
		this.productosPorPaquete = productosPorPaquete;
	}
	
	@DateFieldFormat(format = "dd/MM/yyyy")
	@FieldForm(messageKey = "ProductoViewBean.filter.fechaAlta")
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
	
	
	
}
