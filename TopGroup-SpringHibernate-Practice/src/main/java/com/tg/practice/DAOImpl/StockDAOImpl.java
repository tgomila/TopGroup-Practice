package com.tg.practice.DAOImpl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tg.practice.DAOInterface.StockDAO;
import com.tg.practice.model.Stock;

@Repository
public class StockDAOImpl extends HibernateDAOImpl<Stock> implements StockDAO{
	
	private static StockDAOImpl instancia = null;
	
	protected StockDAOImpl() {
		super(Stock.class);
	}
	
	public static StockDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new StockDAOImpl();
		}
		return instancia;
	}

	//private static Logger log = LoggerFactory.getLogger(StockDAO.class);
	//protected static SessionFactory sessionFactory;
	//protected Session session;
	
	//Métodos extras
	public Stock buscarStockPorProducto(Long idProducto) {
		Session session = null;
		Stock result = null;
		try {
			session = this.openSession();
			
			Criteria crit = session.createCriteria(Stock.class, "stock")
					.setFetchMode("producto", FetchMode.JOIN)
					.createAlias("stock.producto", "p")
					.add(Restrictions.eq("p.id", idProducto))
					;
			result = (Stock) crit.uniqueResult();

			//Puede ser null
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();

		}

		return result;
	}
}