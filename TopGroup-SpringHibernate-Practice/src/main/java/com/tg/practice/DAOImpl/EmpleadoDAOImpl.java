package com.tg.practice.DAOImpl;

import org.springframework.stereotype.Repository;

import com.tg.practice.DAOInterface.EmpleadoDAO;
import com.tg.practice.model2.Empleado;

@Repository
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
