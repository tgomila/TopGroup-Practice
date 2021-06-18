package com.tg.practice.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tg.practice.DAOInterface.PedidoDAO;
import com.tg.practice.model.Pedido;
import com.tg.practice.model.Producto;

public class PedidoDAOImpl extends HibernateDAOImpl<Pedido> implements PedidoDAO{
	
	private static PedidoDAOImpl instancia = null;
	
	protected PedidoDAOImpl() {
		super(Pedido.class);
	}
	
	public static PedidoDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new PedidoDAOImpl();
		}
		return instancia;
	}

	//private static Logger log = LoggerFactory.getLogger(PedidoDAO.class);
	protected static SessionFactory sessionFactory;
	protected Session session;
	
	/**
	 * getSomePedidosConStock
	 * @param cantidadMaximaImpresion Puede ingresar: 
	 * Número 0 o más de pedidos máximos a devolver en list, o null (devuelve todo)
	 * @return List de pedidos, te devuelve la lista con su máximo de pedidos que ingresaste
	 */
	public List<Pedido> getSomePedidosConStock(Long cantidadMaximaImpresion){
		Session session = null;
		session = openSession();
		Criteria crit = session.createCriteria(Pedido.class, "pedido")
				.createAlias("pedido.producto", "producto")
				.createAlias("producto.stock", "stock")
				.add(Restrictions.geProperty("pedido.cantidad", "stock.cantidad")) //ge significa "'G'reater than or 'E'qual"
				;
		//Si me mandan un null, entonces devuelvo todo
		if(cantidadMaximaImpresion != null && cantidadMaximaImpresion > 0)
			crit.setMaxResults( (int)(long) cantidadMaximaImpresion); //long to int
		@SuppressWarnings("unchecked")
		List<Pedido> pedidos = crit.list();
		return pedidos;		
	}
	
	@SuppressWarnings("unchecked")
	public Pedido buscar(Long id) {
		Session session = null;
		Pedido model = null;
		try {
			session = this.openSession();
			
			model = (Pedido) session.get(Pedido.class, id);
			Query query = session.createQuery("select producto "
					+ "from Pedido p "
					+ "where p.id = :id");
			query.setLong("id", id);
			model.setProducto((Producto) query.uniqueResult());
			
			//Puede ser null
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();

		}

		return model;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> getAllComplete(){
		Session session = null;
		session = openSession();
		Criteria crit = session.createCriteria(Pedido.class, "p");
		List<Pedido> pedidos = crit.list();
		List<Pedido> retorna = new ArrayList<Pedido>();
		for(Pedido p: pedidos) {
			Query query = session.createQuery("select producto "
					+ "from Pedido p "
					+ "where p.id = :id");
			query.setLong("id", p.getId());
			p.setProducto((Producto) query.uniqueResult());
			retorna.add(p);
		}
		return retorna;
	}
}