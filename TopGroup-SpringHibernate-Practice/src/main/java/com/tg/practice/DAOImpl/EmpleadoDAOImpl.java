package com.tg.practice.DAOImpl;

import com.tg.practice.DAOInterface.EmpleadoDAO;
import com.tg.practice.model2.Empleado;

public class EmpleadoDAOImpl extends HibernateDAOImpl<Empleado> implements EmpleadoDAO{
	
	private static EmpleadoDAOImpl instancia = null;
	
	protected EmpleadoDAOImpl() {
		super(Empleado.class);
	}
	
	public static EmpleadoDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new EmpleadoDAOImpl();
		}
		return instancia;
	}

}
