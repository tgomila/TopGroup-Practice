package com.tg.practice.DAOImpl;

import com.tg.practice.DAOInterface.FamiliaDAO;
import com.tg.practice.model.Familia;

public class FamiliaDAOImpl extends HibernateDAOImpl<Familia> implements FamiliaDAO{
	
	private static FamiliaDAOImpl instancia = null;
	
	protected FamiliaDAOImpl() {
		super(Familia.class);
	}
	
	public static FamiliaDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new FamiliaDAOImpl();
		}
		return instancia;
	}

}
