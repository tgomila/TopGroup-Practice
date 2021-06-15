package com.tg.practice.DAOImpl;

import com.tg.practice.DAOInterface.TipoProductoDAO;
import com.tg.practice.model.TipoProducto;

public class TipoProductoDAOImpl extends HibernateDAOImpl<TipoProducto> implements TipoProductoDAO{
	
	private static TipoProductoDAOImpl instancia = null;
	
	protected TipoProductoDAOImpl() {
		super(TipoProducto.class);
	}
	
	public static TipoProductoDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new TipoProductoDAOImpl();
		}
		return instancia;
	}

}
