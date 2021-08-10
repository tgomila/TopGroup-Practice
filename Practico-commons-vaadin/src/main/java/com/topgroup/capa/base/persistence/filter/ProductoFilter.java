package com.topgroup.capa.base.persistence.filter;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.domain.model.Provincia;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.commons.vaadin.view.bean.ViewBean;

@Component
@Scope("prototype")
public class ProductoFilter implements ViewBean{
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcion;
	private Date fechaAlta;
	private Short productosPorPaquete;
	private TipoProducto tipoProducto;
	
	
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

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Short getProductosPorPaquete() {
		return productosPorPaquete;
	}

	public void setProductosPorPaquete(Short productosPorPaquete) {
		this.productosPorPaquete = productosPorPaquete;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}


	@Override
	public void initialize() {
		this.codigo = null;
		this.descripcion = null;
		this.fechaAlta = null;
		this.productosPorPaquete = null;
		this.tipoProducto = null;	
	}

}
