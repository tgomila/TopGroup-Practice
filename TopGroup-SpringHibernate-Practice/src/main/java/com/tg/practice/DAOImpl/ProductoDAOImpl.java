package com.tg.practice.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.tg.practice.DAOInterface.ProductoDAO;
import com.tg.practice.model.Maquina;
import com.tg.practice.model.Producto;

public class ProductoDAOImpl extends HibernateDAOImpl<Producto> implements ProductoDAO{
	
	private static ProductoDAOImpl instancia = null;
	
	protected ProductoDAOImpl() {
		super(Producto.class);
	}
	
	public static ProductoDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new ProductoDAOImpl();
		}
		return instancia;
	}

	//private static Logger log = LoggerFactory.getLogger(ProductoDAO.class);
	protected static SessionFactory sessionFactory;
	protected Session session;
	
	@SuppressWarnings("unchecked")
	public List<Producto> getAllComplete(){
		List<Producto> retorna = this.getAll();
		Session session = null;
		session = openSession();
		for(Producto p: retorna) {
			Query query = session.createQuery("select maquinas "
					+ "from Producto p "
					//+ "inner join p.maquinas.productos as produ "
					+ "where p.id = :id");
			query.setLong("id", p.getId());
			p.setMaquinas(query.list());
		}
		
		//No funciono con criteria
		/*try {
			
			Criteria crit = session.createCriteria(Producto.class, "p")
					.createAlias("p.maquinas", "m")
					.createAlias("m.productos", "mp")
					.setFetchMode("m", FetchMode.JOIN)
					.setFetchMode("mp", FetchMode.JOIN)
					;
			
			retorna = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}*/
		return retorna;
	}
}
