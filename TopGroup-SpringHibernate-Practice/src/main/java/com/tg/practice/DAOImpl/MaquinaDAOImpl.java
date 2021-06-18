package com.tg.practice.DAOImpl;

import org.springframework.stereotype.Repository;

import com.tg.practice.DAOInterface.MaquinaDAO;
import com.tg.practice.model.Maquina;

@Repository
public class MaquinaDAOImpl extends HibernateDAOImpl<Maquina> implements MaquinaDAO{
	
	private static MaquinaDAOImpl instancia = null;
	
	protected MaquinaDAOImpl() {
		super(Maquina.class);
	}
	
	public static MaquinaDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new MaquinaDAOImpl();
		}
		return instancia;
	}

}
