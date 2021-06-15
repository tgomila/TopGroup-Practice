package com.tg.practice.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tg.practice.DAOInterface.PedidoDAO;
import com.tg.practice.model.Pedido;

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
		Criteria crit = session.createCriteria(Pedido.class, "pedido")
				.createAlias("pedido.producto", "producto")
				.createAlias("producto.stock", "stock")
				.add(Restrictions.ge("pedido.cantidad", "stock.cantidad")); //ge significa "'G'reater than or 'E'qual"
		
		//Si me mandan un null, entonces devuelvo todo
		if(cantidadMaximaImpresion != null && cantidadMaximaImpresion > 0)
			crit.setMaxResults( (int)(long) cantidadMaximaImpresion); //long to int
		@SuppressWarnings("unchecked")
		List<Pedido> pedidos = crit.list();
		return pedidos;		
	}
}