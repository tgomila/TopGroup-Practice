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
	private TipoProducto tipoProducto;
	private String descripcion;

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public void initialize() {
		this.codigo = null;
		this.tipoProducto = null;
		this.descripcion = null;
	}

}
