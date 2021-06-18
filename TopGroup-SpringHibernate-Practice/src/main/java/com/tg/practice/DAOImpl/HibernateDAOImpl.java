package com.tg.practice.DAOImpl;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.tg.practice.util.HibernateUtil;

//Lo mejor de lo mejor, me ahorro código :D
@Repository
public abstract class HibernateDAOImpl<M> {
	//private static Logger log = LoggerFactory.getLogger(HibernateDAO.class);
	
	//Esto es usando hibernateUtil.java
	//protected static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	//Esto es usando beans, la instancia la trae un bean. Es un singleton, no prototype
	//@Autowired
	protected SessionFactory sessionFactory;
	protected Session session;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
	     this.sessionFactory = sessionFactory;
	  }
	
	//private static final SessionFactory sf = HibernateUtil.getSessionFactory();
	private final Class<M> entityClass;

	protected HibernateDAOImpl(Class<M> entityClass) {
		this.entityClass = entityClass;
	}

	protected Session openSession() {
		return sessionFactory.openSession();
	}
	
	
	
	//save
	public M alta(M model) {
		//sessionFactory = HibernateUtil.getSessionFactory();//new Configuration().configure("hibernate.cfg.xml")
		//						Ya no es necesario al usar "final" en sessionFactory
		//		.buildSessionFactory();
		//log.info("Session factory creado correctamente");
		
		
		Session session = null;
		Transaction transaction = null;
		try {
			
			session = this.openSession();
			transaction = session.beginTransaction();
			session.save(model);
			transaction.commit();
		} catch (RuntimeException re) {
			if(transaction!=null) {
				transaction.rollback();
			}
			re.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		
		return model;
	}
	
	@SuppressWarnings("unchecked")
	public void baja(Long id) {
		Session session = null;
		Transaction transaction = null;
		M model = null;
		try {
			session = this.openSession();
			transaction = session.beginTransaction();
			model = (M) session.get(entityClass, id);
			session.delete(model);
			transaction.commit();
			
			//Puede ser null
		} catch (RuntimeException re) {
			if(transaction!=null) {
				transaction.rollback();
			}

		} finally {
			if (session != null)
				session.close();

		}
	}
	
	//Chequear si funciona
	public void modificar(M model) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			Field fieldId = model.getClass().getDeclaredField("id"); //getDeclaredField para acceder a "private" id
			fieldId.setAccessible(true); // suppress Java access checking
			Long longId = null;
			longId = new Long((long)fieldId.get(model));
						
			//Field somePrivateField = model.getClass().getDeclaredField("id");
		    //somePrivateField.setAccessible(true); // Subvert the declared "private" visibility
		    //Object fieldValue = somePrivateField.get(someInstance);
			
			//Si existe un id, se va a modificar en el método "alta"
			if(longId != null && longId > 0) {
				//this.alta(model);	//Con este método se ahorra código, dentro del método se llama a SaveOrUpdate()
				session = this.openSession();
				transaction = session.beginTransaction();
				session.saveOrUpdate(model);
				transaction.commit();
				
			}
		} catch (RuntimeException | NoSuchFieldException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	//findById
	@SuppressWarnings("unchecked")
	public M buscar(Long id) {
		//sessionFactory = HibernateUtil.getSessionFactory(); //Ya no es necesario al usar "final" en sessionFactory
		//sessionFactory = new Configuration().configure("hibernate.cfg.xml")
		//		.buildSessionFactory();
		//log.info("Session factory creado correctamente");
		
		Session session = null;
		M model = null;
		try {
			session = this.openSession();
			
			model = (M) session.get(entityClass, id);

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
	public List<M> getAll() {
		//sessionFactory = new Configuration().configure("hibernate.cfg.xml")
		//		.buildSessionFactory();
		//log.info("Session factory creado correctamente");
		
		List<M> retorna = null;
		Session session = null;
		try {
			session = openSession();
			Criteria crit = session.createCriteria(entityClass);
			
			retorna = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return retorna;
	}
	
	public boolean doesObjectContainField(Object object, String fieldName) {
	    Class<?> objectClass = object.getClass();
	    for (Field field : objectClass.getFields()) {
	        if (field.getName().equals(fieldName)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/*@SuppressWarnings("unchecked")
	public void borrarTodo() {
		
		Session session = null;
		try {
			session = openSession();
			Criteria crit = session.createCriteria(entityClass, "entity")
					.createAlias("producto", alias);
			List<M> listToDelete = crit.list();
			session.delete(listToDelete);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}*/
}
