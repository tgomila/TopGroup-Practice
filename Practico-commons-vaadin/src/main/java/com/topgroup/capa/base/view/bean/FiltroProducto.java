package com.topgroup.capa.base.view.bean;

import com.topgroup.commons.vaadin.view.bean.ViewBean;

//ProductoViewBean
public class FiltroProducto implements ViewBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6038688298818288340L;
		
	private String codigo;
	private String tipoProducto;
	//tipo producto
	
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	
	//Getters and Setters
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	

}
