package com.tg.practice.DAOImpl;

import java.util.ArrayList;
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
		Session session = null;
		session = openSession();
		Criteria crit = session.createCriteria(Producto.class, "p")
				//Creí que esto podía reemplazar el join, pero esto me genera 2 productos id1 si tiene 2 maquinas.
				//.createAlias("p.familia", "fam")
				//.createAlias("p.tipoProducto", "tipoProd")
				//.createAlias("p.maquinas", "maq")
				//.createAlias("p.stock", "stock")
				;
		List<Producto> productos = crit.list();
		List<Producto> retorna = new ArrayList<Producto>();
		for(Producto p: productos) {
			Query query = session.createQuery("select maquinas "
					+ "from Producto p "
					//+ "inner join p.maquinas.productos as produ "
					+ "where p.id = :id");
			query.setLong("id", p.getId());
			p.setMaquinas(query.list());
			retorna.add(p);
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
	
	
	//Sobrescribo el buscar id de HibernateImpl, para poder agregarle las máquinas.
	@SuppressWarnings("unchecked")
	public Producto buscar(Long id) {
		Session session = null;
		Producto model = null;
		try {
			session = this.openSession();
			
			model = (Producto) session.get(Producto.class, id);
			Query query = session.createQuery("select maquinas "
					+ "from Producto p "
					//+ "inner join p.maquinas.productos as produ "
					+ "where p.id = :id");
			query.setLong("id", id);
			model.setMaquinas(query.list());
			
			//Puede ser null
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();

		}

		return model;
	}
}
