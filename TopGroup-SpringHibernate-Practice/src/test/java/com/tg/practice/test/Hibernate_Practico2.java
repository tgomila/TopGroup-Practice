package com.tg.practice.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tg.practice.DAOImpl.*;
import com.tg.practice.model.*;
import com.tg.practice.model2.*;

//Quitar el //de los "@test" para que se ejecuten
public class Hibernate_Practico2 {
	private static Logger log = LoggerFactory.getLogger(Hibernate_Practico2.class);
	protected static SessionFactory sessionFactory;
	protected Session session;

	@BeforeClass
	public static void initialize() {

		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
		log.info("Session factory creado correctamente");
	}

	@AfterClass
	public static void endTest() {
		sessionFactory.close();
	}
	
	@Before //Se ejecuta antes de cada test
	public void setUp() throws Exception {
		session = sessionFactory.openSession();
        //System.out.println("Session created");
        log.info("Session created");
	}
	
	@After //Se ejecuta despues de cada test
    public void closeSession() {
        if (session != null && session.isOpen())
        	session.close();
        //System.out.println("Session closed\n");
        log.info("Session closed\n");
    }
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	//@Test
	public void test0() {
		//Test basico para ver si lee el 1er empleado
		log.info("");
		log.info("************** BASIC TEST 0 *******************");
		
		//session = sessionFactory.openSession();
		Long empleadoId = Long.parseLong("1");
		Empleado empleado = (Empleado) session.get(Empleado.class, empleadoId);
		System.out.println("Nombre empleado: " + empleado.getNombre());
		log.info("Nombreeeeee empleado: " + empleado.getNombre());
		//if (session != null)
		//	session.close();
		/*session = sessionFactory.openSession();
        System.out.println("Session created");
        
        Transaction tx = session.beginTransaction();
        
        Criteria cr = session.createCriteria(Puesto.class);
        cr.add(Restrictions.eq("Nombre", "Analista"));
        Puesto puesto = (Puesto) cr.uniqueResult();
        EmpleadoContratado empleado = new EmpleadoContratado();
        empleado.setPuesto(puesto);
        
        if (session != null)
        	session.close();
        
        System.out.println("Session closed\n");*/
		log.info("");
		log.info("************** FIN -BASIC TEST 0 *******************");

	}
	
	@SuppressWarnings("deprecation")
	//@Test
	public void ejercicio1_CrearEmpleado() {
		log.info("");
		log.info("************** TEST 1 - HQL*******************");
		EmpleadoContratado e = new EmpleadoContratado();
		
		//Seteo fecha de hoy, hora 00:00. y seteo fecha del año que viene fin de contrato
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		log.info("Date today: " +today);
		e.setFechaInicioContrato(today); //Fecha de hoy
		Date oneMoreYear = today;
		oneMoreYear.setYear(today.getYear()+1);
		log.info("Date one more year: " +oneMoreYear);
		e.setFechaFinContrato(oneMoreYear);
		e.setTipoContrato(TipoContrato.ANUAL);
		//Fin fechas
		
		//Seteo datos de clase "Empleado"
		e.setApellido("Gonzalez");
		e.setNombre("Fulanito");
		e.setFechaNacimiento(new Date(2000, 1, 1));
		e.setnLegajo(1);
		e.setDni(12345678);
		e.setCuil("20-12345678-4");
		e.setFechaIngreso(new Date());
		e.setSueldo(30000);
		
		//Busco puesto "Analista" en la BD, y se lo adjunto al empleado
		//session = sessionFactory.openSession();
		Query query = session.createQuery("from Puesto p where p.nombre = :nombrePuesto");
		query.setString("nombrePuesto", "Analista");
		Puesto puestoE = (Puesto) query.uniqueResult();
		e.setPuesto(puestoE);
		//if (session != null)
		//	session.close();
		
		//Busco Sucursal "A".
		query = session.createQuery("from Sucursal s where s.descripcion = :descripcionEntry");
		query.setString("descripcionEntry", "Sucursal A");
		Sucursal sucuE = (Sucursal) query.uniqueResult();
		e.setSucursalPrincipal(sucuE);
		
		/* Recorrido
		query = session.createQuery("from Sucursal");
		List<Sucursal> sucursales = query.list();
		for(Sucursal s: sucursales) {
			if(s == null)
				log.info("Es null for");
			else
				log.info("S=" + s.getDescripcion());
		}*/
		
		
		
		
		
		
		//Busco sucursales
		//Sucursal A
		e.getSucursalesHabilitadas().add(sucuE); //Ya estaba buscada
		
		//Sucursal B
		query = session.createQuery("from Sucursal s where s.descripcion = :descripcionEntry");
		query.setString("descripcionEntry", "Sucursal B");
		sucuE = (Sucursal) query.uniqueResult();
		e.getSucursalesHabilitadas().add(sucuE);
		
		//Sucursal C
		query = session.createQuery("from Sucursal s where s.descripcion = :descripcionEntry");
		query.setString("descripcionEntry", "Sucursal C");
		sucuE = (Sucursal) query.uniqueResult();
		e.getSucursalesHabilitadas().add(sucuE);
		
		//Guardo empleado
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(e);
		tx.commit();
		
		
		
		//Corroboro que se haya guardado
		query = session.createQuery("from Empleado e where e.apellido = :apellidoEntry");
		query.setString("apellidoEntry", "Gonzalez");
		Empleado saved = (Empleado) query.uniqueResult();
		if(saved != null)
			log.info("Empleado guardado.  Nombre: " + saved.getNombre() + ".   Apellido: " + saved.getApellido());
		else
			log.info("No se guardó el nuevo empleado");
		
		
		
		
		
		log.info("");
		log.info("************** FIN - TEST 1 *******************");

	}
	
	
	
	
	
	
	//@Test
	public void ejercicio2_ModificarEmpleado() {
		/* Consigna:
		 * 2.	Abra una nueva sesión de hibernate  y busque el  fichaje del empleado Pedro Lopez,
		 * 		dni 25432346 correspondiente al día 16/04/2013 . Luego cierre la sesión.
		 *		Actualice la siguiente información: en una nueva sesión:
		 *		• el motivo del fichaje manual se debió una urgencia.
		 *		• Cambie la sucursal del fichaje a la sucursal E (id=1).
		 *		• Agregue un nombre de usuario responsable de la modificación , junto con la fecha de modificación.
		 */
		log.info("");
		log.info("************** TEST 2 - Criteria*******************");
		
		try {
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String myDate = "16/04/2013";
		    // Create date 16-04-2013 - 00h00
		    Date minDate = formatter.parse(myDate);
		    // Create date 17-04-2013 - 00h00 
		    // -> We take the 1st date and add it 1 day in millisecond thanks to a useful and not so known class
		    Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
		    
		   log.info("minDate: " + minDate.toString() + "  maxDate: " + maxDate);
		    
		    Criteria crit = session.createCriteria(Fichaje.class)
					.add(Restrictions.ge("ingreso", minDate))
					.add(Restrictions.le("ingreso", maxDate))
					.createCriteria("empleado")
						.add(Restrictions.eq("nombre", "Pedro"))
						.add(Restrictions.eq("apellido", "Lopez"))
						.add(Restrictions.eq("dni", 25432346));
		    		/*.createAlias("fichaje.empleado", "e")
					.add(Restrictions.eq("e.nombre", "Pedro"))
					.add(Restrictions.eq("e.apellido", "Lopez"))
					.add(Restrictions.eq("e.dni", 25432346))*/
			crit.setMaxResults(50);
			@SuppressWarnings("unchecked")
			List<Fichaje> fichajes = crit.list();
			
			session.close();
			
			//imprimo fichajes de ese día y obtengo el fichaje manual, aparentemente es 1 solo fichaje
			Fichaje fichajeManual = null;
			if(fichajes!=null && fichajes.size() > 0) {
				log.info("Fichaje: " + fichajes.size());
				for(Fichaje item: fichajes) {
					log.info("ingreso: " + item.getIngreso());
					log.info("empleado: " + item.getEmpleado().getNombre() + " " + item.getEmpleado().getApellido());
					log.info("dni: " + item.getEmpleado().getDni());
					log.info("Tipo fichaje: " + item.getTipoFichaje());
					if(item.getTipoFichaje()!=null && item.getTipoFichaje().toString().equalsIgnoreCase("MANUAL"))
						fichajeManual = item;
						
				}
				
			}
			else
				log.info("No hay fichaje");
			
			
			if(fichajeManual != null) {
				session = sessionFactory.openSession();
				//•	el motivo del fichaje manual se debió una urgencia.
				fichajeManual.getFichajeExtra().setMotivoFichajeManual(MotivoFichajeManual.URGENCIAS);
				//•	Cambie la sucursal del fichaje a la sucursal E (id=1).
				Criteria crite = session.createCriteria(Sucursal.class)
						.add(Restrictions.eq("id",new Long(1)));
				Sucursal e = (Sucursal) crite.uniqueResult();
				fichajeManual.setSucursal(e);
				//•	Agregue un nombre de usuario responsable de la modificación , junto con la fecha de modificación.
				fichajeManual.getFichajeExtra().setUsuario("DonFulanito");
				
				//Realizo la modificación en la bd
				
				
				Transaction	tx = session.beginTransaction ();
				/*Fichaje savedObject = (Fichaje)*/ session.save(fichajeManual);
				tx.commit();
				
				
				//Chequeo el dato modificado
				Fichaje f = (Fichaje) session.createCriteria(Fichaje.class)
						.add(Restrictions.eq("id",fichajeManual.getId())).uniqueResult();
				if(f!=null) {
					log.info("ingreso: " + f.getIngreso());
					log.info("empleado: " + f.getEmpleado().getNombre() + " " + f.getEmpleado().getApellido());
					log.info("dni: " + f.getEmpleado().getDni());
					log.info("Tipo fichaje: " + f.getTipoFichaje());
					if(f.getFichajeExtra()!=null) {
						log.info("Fichaje extra. Usuario: " + f.getFichajeExtra().getUsuario());
						log.info("Fichaje extra: " + f.getFichajeExtra().getMotivoFichajeManual());
						if(f.getFichajeExtra().getUsuario().equalsIgnoreCase("DonFulanito"))
							log.info("Dado que DonFulanito esta en fichaje extra: Fichaje modificado con éxito! :D");
					}
					else
						log.info("No hay fichaje extra, revisar");
				}
				else
					log.info("No hay fichaje, revisar");
				session.close();
			}
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		log.info("");
		log.info("************** FIN - TEST 2 *******************");

	}
	
	
	//Aclaro que si hay tests con dao’s dentro de Hibernate_Practico2.java, fueron
	//testeados antes del uso de beans y quizás ahora no funcionen
	//@Test
	public void testDAO() {
		log.info("");
		log.info("************** TEST DAO - Criteria DAO*******************");
		EmpleadoDAOImpl empleadoDAO = EmpleadoDAOImpl.getInstancia();
		Empleado test = empleadoDAO.buscar(Long.parseLong("1"));
		if(test != null)
			System.out.println("Empleado: " + test.getApellido());
		else
			System.out.println("No existe test empleado");
		
		log.info("");
		log.info("************** FIN - TEST DAO *******************");

	}
	
	public void imprimirFichajes(List<Fichaje> fichajes) {
		//imprimo fichajes
		//Fichaje fichajeManual = null;
		if(fichajes!=null && fichajes.size() > 0) {
			log.info("Cantidad fichajes: " + fichajes.size());
			for(Fichaje item: fichajes) {
				imprimirFichaje(item);
				//if(item.getTipoFichaje()!=null && item.getTipoFichaje().toString().equalsIgnoreCase("MANUAL"))
				//	fichajeManual = item;
					
			}
			
		}
		else
			log.info("No hay fichaje");
	}
	
	public void imprimirFichaje(Fichaje fichaje) {
		log.info("ingreso: " + fichaje.getIngreso());
		log.info("empleado: " + fichaje.getEmpleado().getNombre() + " " + fichaje.getEmpleado().getApellido());
		if(fichaje.getFichajeExtra()!=null)
			log.info("extra usuario: " + fichaje.getFichajeExtra().getUsuario());
		else
			log.info("No hay fichaje extra");
		log.info("dni: " + fichaje.getEmpleado().getDni());
		log.info("Tipo fichaje: " + fichaje.getTipoFichaje());
		log.info("Sucursal: " + fichaje.getSucursal().getLocalidad().getNombre());
		log.info("---");
	}
	
	//@Test
	public void test3() {
		//3.	Elimine todos aquellos fichajes automáticos de los empleados permanentes que
		//		hayan trabajado en la ciudad de Santa Fe entre el 7 çy el 9 de marzo del 2013
		
		log.info("");
		log.info("************** TEST 3 - Criteria *******************");
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String myDate = "07/03/2013";
		    // Create date 07-03-2013 - 00h00
		    Date minDate = formatter.parse(myDate);
		    // Create date 10-03-2013 - 00h00 
		    // -> We take the 1st date and add it 3 days in millisecond thanks to a useful and not so known class
		    Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(3));
			
		    Criteria crit0 = session.createCriteria(EmpleadoPermanente.class)
		    			.setProjection(Projections.property("id"));
		    @SuppressWarnings("unchecked")
			List<Long> ids = crit0.list();
		    if(ids!=null && ids.size()>0) {
		    	for(Long id: ids) {
			    	System.out.println("id: " + id);
			    }
		    }
		    else
		    	System.out.println("No hay ids");
		    
		    
		    Criteria crit = session.createCriteria(Fichaje.class, "fichaje")
						.setFetchMode("empleado", FetchMode.JOIN)		//Esto es para cerrar la sesión
						.setFetchMode("sucursal", FetchMode.JOIN)
						.setFetchMode("sucursal.localidad", FetchMode.JOIN)
						.add(Restrictions.between("ingreso", minDate, maxDate))	//entre el 7 y el 9 de marzo del 2013
						
						.createAlias("fichaje.sucursal", "suc")		//que hayan trabajado en la ciudad de Santa Fe
						.createAlias("suc.localidad", "sucloc")
						.add(Restrictions.eq("sucloc.nombre", "Santa Fe"))
						
						.add(Restrictions.isNull("tipoFichaje"))	//fichaje automatico es null
						//Empleados permanentes?
		 				;
			
			crit.setMaxResults(50);
			@SuppressWarnings("unchecked")
			List<Fichaje> fichajes = crit.list();
			imprimirFichajes(fichajes);
			
			Criteria critEmplePermanente = session.createCriteria(EmpleadoPermanente.class, "ep")
					.setProjection(Projections.property("id"));
			@SuppressWarnings("unchecked")
			List<Long> empleadosPermanentesIds = critEmplePermanente.list();
			
			//Verifico que ese fichaje sea de un empleado permanente, de ser asi lo borro
			for(Fichaje f: fichajes) {
				if(empleadosPermanentesIds.contains(f.getEmpleado().getId())) {
					log.info("Borro fichero id: " + f.getId() + 
							" por su empleado permanente de id: " + f.getEmpleado().getId());
					session.delete(f); //F fichero
					Fichaje deleted = (Fichaje) session.get(Fichaje.class, new Long(f.getId()));
					if(deleted == null)
						log.info("Ya fue borrado");
					else
						log.info("No fue borrado, chequear");
				}
			}
			
			session.close();
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		log.info("");
		log.info("************** FIN - TEST 3 *******************");

	}
	
	//@Test
	public void test4() {
		log.info("");
		log.info("************** INICIO TEST 4 - Criteria *******************");
		//4.	Cree una nueva sucursal "Sucursal F" y asociela a todos los empleados.
		//Busco una localidad para mi nueva sucursal
		Transaction tx = session.beginTransaction ();
		Localidad locSantaFe = (Localidad) session.get(Localidad.class, new Long("1"));
		tx.commit();
		if(locSantaFe!=null) {
			Sucursal nuevo = new Sucursal();
			nuevo.setNomenclador("0");
			nuevo.setDescripcion("Sucursal F");
			nuevo.setDireccion("Calle 1234");
			nuevo.setCentral(false);
			nuevo.setLocalidad(locSantaFe);
			Criteria crit =  session.createCriteria(Empleado.class, "e");
			@SuppressWarnings("unchecked")
			List<Empleado> todos = crit.list();
			nuevo.setEmpleadosHabilitados(todos);
			tx = session.beginTransaction ();
			session.save(nuevo);
			tx.commit();
			
			//Verifico
			Sucursal saved = null;
			tx = session.beginTransaction ();
			saved = (Sucursal) session.get(Sucursal.class, new Long("6"));
			tx.commit();
			if(saved != null && saved.getDescripcion().equalsIgnoreCase(nuevo.getDescripcion()))
				log.info("Guardado correctamente");
			else
				log.info("No ha sido guardado");
			
		}
		
		
		log.info("");
		log.info("************** FIN - TEST 4 *******************");

	}
	
	//@Test
	public void test5() {
		log.info("");
		log.info("************** INICIO TEST 5 - Criteria *******************");
		//5.	Obtener todos los fichajes de los empleados habilitados en la sucursal A que
		//		hayan necesitado que se realice un fichaje manual.
		Criteria crit = session.createCriteria(Fichaje.class, "fichaje")
				//.setFetchMode("empleado", FetchMode.JOIN)		//Esto es para cerrar la sesión
				//.setFetchMode("sucursal", FetchMode.JOIN)
				//.setFetchMode("sucursal.localidad", FetchMode.JOIN)
				
				.createAlias("fichaje.sucursal", "suc")		//que hayan trabajado en la ciudad de Santa Fe
				.add(Restrictions.eq("suc.descripcion", "Sucursal A"))
				
				.add(Restrictions.eq("tipoFichaje", TipoFichaje.MANUAL))	//fichaje automatico es null
				//Empleados permanentes?
 				;
	
	//crit.setMaxResults(50);
	@SuppressWarnings("unchecked")
	List<Fichaje> fichajes = crit.list();
	
	//session.close();
	imprimirFichajes(fichajes);
		
		log.info("");
		log.info("************** FIN - TEST 5 *******************");

	}
	
	public void imprimirSucursal(List<Sucursal> sucursales) {
		//imprimo sucursales
		if(sucursales!=null && sucursales.size() > 0) {
			log.info("Cantidad sucursales: " + sucursales.size());
			for(Sucursal item: sucursales) {
				log.info("id: " + item.getId());
				log.info("descripcion: " + item.getDescripcion());
			}
			
		}
		else
			log.info("No hay sucursal");
	}
	
	//@Test
	public void test6() {
		
		//6.	¿Cuántos fichajes de empleados con contrato anual se han realizado
		//		en la ciudad de Córdoba en los últimos 2 meses ?
		
		log.info("");
		log.info("************** INICIO TEST 6 - HQL *******************");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		cal.set(Calendar.YEAR, 2013);	//Lo seteo en 2013 porque sino no voy a tener resultados xd
		Date minDate = cal.getTime();
		
		cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, 2013);
		Date maxDate = cal.getTime();
		log.info("minDate: " + minDate.toString() + "  maxDate: " + maxDate);
		
		
		
		Query query = session.createQuery("select f "
				+ "from Fichaje f, EmpleadoContratado e "
				+ "where f.empleado.id = e.id "
				+ "and f.ingreso between :iniDate and :endDate "
				+ "and f.sucursal.localidad.nombre = :ciudad "
				//No estoy seguro porque no me toma esto
				//+ "and e.tipoContrato = '"+ TipoContrato.ANUAL.name() + "'"
				)
		.setParameter("iniDate", minDate)
		.setParameter("endDate", maxDate);
		query.setString("ciudad", "C\\u00f3rdoba");
		@SuppressWarnings("unchecked")
		List<Fichaje> fichajes = query.list();
		
		//Busco los empleados permanentes con contrato anual
		Query query2 = session.createQuery("select e "
				+ "from EmpleadoContratado e "
				//No estoy seguro porque no me toma esto
				//+ "where e.tipoContrato = '"+ TipoContrato.ANUAL.name() + "'"
				);
		@SuppressWarnings("unchecked")
		List<EmpleadoContratado> ee = query2.list(); //Todos los empleados contratados
		List<Long> idsEmpleAnuales = new ArrayList<Long>();
		log.info("size: " + ee.size());
		for(EmpleadoContratado item: ee) {
			log.info("id: " + item.getId() + "  tipoContrato: " + item.getTipoContrato().name());
			if(TipoContrato.ANUAL.equals(item.getTipoContrato())) {
				idsEmpleAnuales.add(item.getId());
			}
		}
		
		//limpio fichajes solo con empleados anuales
		List<Fichaje> newFichajes = new ArrayList<Fichaje>();
		if(fichajes!=null && fichajes.size() > 0) {
			log.info("Cantidad fichajes: " + fichajes.size());
			for(Fichaje itemFichaje: fichajes) {
				//si el itemFichaje es un empleado anual, lo agrego a la nueva lista
				if(idsEmpleAnuales.contains(itemFichaje.getEmpleado().getId()))
					newFichajes.add(itemFichaje);
			}
		}
		else
			log.info("No hay fichaje");
		
		//imprimirFichajes(newFichajes);
		
		log.info("Elimino newFichajes de la base de datos");
		for(Fichaje itemFichaje: newFichajes) {
			//Extra para chequear
			Fichaje saved = (Fichaje) session.get(Fichaje.class, itemFichaje.getId());
			if(saved == null)
				log.info("Fichaje id: " + itemFichaje.getId() + " no existe en la bd, chequear");
			else
				log.info("Fichaje id: " + itemFichaje.getId() + " existe en la bd. Borrando...");
			//Fin extra
			
			session.delete(itemFichaje);
			//Chequeo si fue borrado
			Fichaje deleted = (Fichaje) session.get(Fichaje.class, itemFichaje.getId());
			if(deleted == null)
				log.info("Fichaje id: " + itemFichaje.getId() + " fue borrado");
			else
				log.info("Fichaje id: " + itemFichaje.getId() + " no fue borrado");
		}
		
		log.info("");
		log.info("************** FIN - TEST 6 *******************");

	}
	
	//@Test
	public void test7() {
		//7.	Obtenga  los  empleados permanentes que cobren un bono de más del 30%
		// Luego de cerrar la sesión, imprima en consola:
		//	•	Nombre y apellido
		//	•	Nombre del puesto que ocupa
		//	•	Sucursales en las que se encuentra habilitado
		//	•	Ciudades de las sucursales en las que se encuentra habilitado
		//
		// Resuelva el problema de lazy initialization de 3 maneras distintas.
		
		log.info("");
		log.info("************** INICIO TEST 7 - HQL *******************");
		
		//Criteria crit = session.createCriteria(EmpleadoPermanente.class, "empleadoPermanente");
		
		/*Criteria crit = session.createCriteria(EmpleadoPermanente.class, "empleadoPermanente")
				.createAlias("empleadoPermanente.sucursalesHabilitadas", "suchab")
				.createAlias("suchab.localidad", "loc")
				.setFetchMode("puesto", FetchMode.JOIN)		//Esto es para cerrar la sesión
				.setFetchMode("suchab", FetchMode.JOIN)
				.setFetchMode("loc", FetchMode.JOIN)
				
				//.add(Restrictions.ge("porcentajeBono", new Double("30")));
				
				///add(Restrictions.between("ingreso", minDate, maxDate))	//entre el 7 y el 9 de marzo del 2013
				
				//.createAlias("fichaje.sucursal", "suc")		//que hayan trabajado en la ciudad de Santa Fe
				//.createAlias("suc.localidad", "sucloc")
				//.add(Restrictions.eq("sucloc.nombre", "Santa Fe"))
				
				//.add(Restrictions.isNull("tipoFichaje"))	//fichaje automatico es null
				//Empleados permanentes?
 				;
		
		
		crit.setMaxResults(50);
		@SuppressWarnings("unchecked")
		List<Empleado> empleados = crit.list();*/
		
		Query query = session.createQuery("select e "
				+ "from EmpleadoPermanente e "
				//+ "fetch all properties "
				//+ "left join fetch e.puesto p "
				//+ "left join fetch e.sucursalesHabilitadas sh"
				//+ "left join fetch e.sucursalesHabilitadas.localidad "
				+ "where 30 < any(select ep.porcentajeBono from EmpleadoPermanente ep)"
				);
		//Nose como hacer funcionar el fetch, asique aqui una solucion temporal
		@SuppressWarnings("unchecked")
		List<Empleado> empleados = query.list();
		for(Empleado e: empleados) {
			for (Sucursal s: e.getSucursalesHabilitadas())
				s.getLocalidad().getNombre();
		}
		
		session.close();
		imprimirEmpleados(empleados);
		
		
		log.info("");
		log.info("************** FIN - TEST 7 *******************");
		
	}
	
	public void imprimirEmpleados(List<Empleado> empleados) {
		//imprimo fichajes
		//Fichaje fichajeManual = null;
		if(empleados!=null && empleados.size() > 0) {
			log.info("Cantidad empleados: " + empleados.size());
			for(Empleado item: empleados) {
				imprimirEmpleado(item);				
			}
		}
		else
			log.info("No hay empleados");
	}
	public void imprimirEmpleado(Empleado empleado) {
		log.info("Nombre: " + empleado.getNombre() +
				"  Apellido: " + empleado.getApellido() +
				"  Puesto: " + empleado.getPuesto().getNombre() +
				"  Fecha de nacimiento: " + empleado.getFechaNacimiento()
				);
		if(empleado.getSucursalesHabilitadas()!=null && empleado.getSucursalesHabilitadas().size()>0) {
			log.info("Sucursales habilitadas (total " + empleado.getSucursalesHabilitadas().size() + "):");
			for(Sucursal s: empleado.getSucursalesHabilitadas()) {
				imprimirSucursal(s);
			}
		}
		else {
			log.info("No tiene sucursales habilitadas");
		}
		log.info("");
	}
	
	public void imprimirSucursal(Sucursal sucursal) {
		log.info("    Nomenclador: " + sucursal.getNomenclador() +
				"   Descripcion: " + sucursal.getDescripcion() +
				"    Ciudad: " + sucursal.getLocalidad().getNombre());
	}
	
	
	//@Test
	public void test8() {
		log.info("");
		log.info("************** INICIO TEST 8 - HQL/Criteria *******************");
		//8.	Indique la cantidad total de fichajes, y el empleado más joven  por puesto
		//		de trabajo y sucursal.
		
		//Cantidad total de fichajes
		Query query = session.createQuery("select count(*), max(f.id) "
				+ "from Fichaje f"
				);
		@SuppressWarnings("rawtypes")
		List l = query.list();
		Object result[] = (Object[]) l.get(0);
		
		Long res1 = (Long) result[0];
	    long count = res1.longValue();
	    
	    Long res2 = (Long) result[1];
	    long max = res2.longValue();
	    
	    log.info("Cantidad de fichajes en base de datos: " + count);
	    log.info("Número máximo de ID en base de datos: " + max);
	    
	    
	    //Empleado mas joven
		Query query2 = session.createQuery("select e "
				+ "from Empleado e "
				+ "where e.fechaNacimiento = (select max(eAux.fechaNacimiento) "
					+ "from Empleado eAux) "
				);
		Empleado res = (Empleado) query2.uniqueResult();
		log.info("Empleado mas joven de todas las sucursales:");
		imprimirEmpleado(res);
		
		log.info("\n\nBusco empleados mas jovenes\n\n");
		//Empleado mas joven por sucursal, y por puesto
		Query query3 = session.createQuery("select "
				+ "e.sucursalPrincipal.id, "//e.sucursalPrincipal.descripcion, "
				//+ "e.id, e.nombre, e.apellido, "
				+ "max(e.fechaNacimiento) "
				+ "from Empleado e "
				+ "group by e.sucursalPrincipal.id, e.puesto.id"
				);
		@SuppressWarnings("rawtypes")
		List l2 = query3.list();
		
		for(int i=0; i<l2.size(); i++) {
			Object result2[] = (Object[]) l2.get(i);
			log.info("Datos a buscar:");
			log.info("id sucursal principal: " + result2[0]
					+ "   fecha nacimiento del empleado mas joven: " + result2[1]
					//+ "   puesto: " + result2[3]
					);
		}
		
		for(int i=0; i<l2.size(); i++) {
			Object result2[] = (Object[]) l2.get(i);
			
			log.info("Datos a buscar:");
			log.info("id sucursal principal: " + result2[0] +
					"   fecha nacimiento del empleado mas joven: " + result2[1]
					);
			Query query4 = session.createQuery("select e "
					+ "from Empleado e "
					+ "where e.sucursalPrincipal.id = :idSucursal and e.fechaNacimiento = :fechaNac"
					)
					.setParameter("idSucursal", result2[0])
					.setParameter("fechaNac", result2[1])
					;
			Empleado emple = (Empleado) query4.uniqueResult();
			//Obtuve datos
			log.info("Sucursal:");
			log.info("id: " + emple.getSucursalPrincipal().getId() +
					"   descripcion: " + emple.getSucursalPrincipal().getDescripcion()
					);
			log.info("Puesto: " + emple.getPuesto().getNombre());
			log.info("Empleado:");
			log.info("id: " + emple.getId() + "." +
					"   nombre: " + emple.getNombre() + "." +
					"   apellido: " + emple.getApellido() + "." +
					"   fecha de nacimiento:" + emple.getFechaNacimiento()
					);
			log.info("");
		}
		
		//Intentos que no se pudieron
//		@SuppressWarnings("rawtypes")
//		List<Object[]> l2 = query3;
//		Object result2[] = (Object[]) l2.get(0);		
//		@SuppressWarnings("unchecked")
//		List<Empleado> empleadosJovenesPorSucursal = (List<Empleado>) result2[0];
//		
//		
//		//Criteria crit = session.createCriteria(Empleado.class, "empleado")
//		//			.setProjection(Projections.groupProperty("sucursalPrincipal"));
//		//List<Empleado> empleados = crit.list();
//		
//		Criteria crit2 = session.createCriteria(Empleado.class)
//				.setProjection(Projections.projectionList()
//						.add(Projections.property("id").as("id") )
//						.add(Projections.property("nombre").as("nombre") )
//						.add(Projections.property("apellido").as("apellido") )
//                        .add(Projections.groupProperty("sucursalPrincipal").as("sucursalPrincipal"))
//                        //.add(Projections.groupProperty("puesto").as("puesto"))
//                        //.add(Projections.max("fechaNacimiento"))
//                        //.add(Projections.min("someColumn"))
//                        //.add(Projections.count("someColumn"))           
//                );
//		crit2.setResultTransformer(Transformers.aliasToBean(Empleado.class));
//		List<Empleado> em = crit2.list();
//		
//		for(Empleado it: em){
//		    System.out.println(it.getNombre());
//		}
//		
//		
//		List<Object[]> cositas = crit2.list();
//		Object cositasCero[] = (Object[]) l.get(0);
//		Long s = (Long) cositasCero[1];
//		log.info("long: " + s);
//		
//		
//		for(Empleado e: empleadosJovenesPorSucursal) {
//			log.info("Empleado mas joven de la sucursal:");
//			imprimirSucursal(e.getSucursalPrincipal());
//			log.info("Nombre del empleado: " + e.getNombre()
//				+ "  Apellido: " + e.getApellido()
//				+ "  Fecha nacimiento: " + e.getFechaNacimiento()
//			);
//		}
//		
//		Criteria crit2 = session.createCriteria(Fichaje.class, "fichaje")
//					.setFetchMode("empleado", FetchMode.JOIN)		//Esto es para cerrar la sesión
//					.setFetchMode("sucursal", FetchMode.JOIN)
//					.setFetchMode("sucursal.localidad", FetchMode.JOIN)
//					.add(Restrictions.between("ingreso", minDate, maxDate))	//entre el 7 y el 9 de marzo del 2013
//					
//					.createAlias("fichaje.sucursal", "suc")		//que hayan trabajado en la ciudad de Santa Fe
//					.createAlias("suc.localidad", "sucloc")
//					.add(Restrictions.eq("sucloc.nombre", "Santa Fe"))
//					
//					.add(Restrictions.isNull("tipoFichaje"))	//fichaje automatico es null
//					//Empleados permanentes?
//	 				;
//		
//		crit2.setMaxResults(50);
//		@SuppressWarnings("unchecked")
//		List<Fichaje> fichajes = crit2.list();
//		imprimirFichajes(fichajes);
//		
//		Criteria crit = session.createCriteria(Sucursal.class, "sucursal")
//				.setFetchMode("empleado", FetchMode.JOIN)		//Esto es para cerrar la sesión
//				.setFetchMode("sucursal", FetchMode.JOIN)
//				.setFetchMode("sucursal.localidad", FetchMode.JOIN)
//				.add(Restrictions.between("ingreso", minDate, maxDate))	//entre el 7 y el 9 de marzo del 2013
//				
//				.createAlias("fichaje.sucursal", "suc")		//que hayan trabajado en la ciudad de Santa Fe
//				.createAlias("suc.localidad", "sucloc")
//				.add(Restrictions.eq("sucloc.nombre", "Santa Fe"))
//				
//				.add(Restrictions.isNull("tipoFichaje"))	//fichaje automatico es null
//				//Empleados permanentes?
// 				;
//	
//		crit.setMaxResults(50);
//		@SuppressWarnings("unchecked")
//		List<Fichaje> fichajes = crit.list();
//		imprimirFichajes(fichajes);
		
		
		log.info("");
		log.info("************** FIN - TEST 8 *******************");

	}
	
	//@Test
	public void test9() {
		log.info("");
		log.info("************** INICIO TEST 9 - HQL/Criteria *******************");
		//9.	¿Cuántos fichajes manuales fueron realizados por el usuario fmateo para empleados que tienen hijos?
		
		//Cantidad total de fichajes
		Query query = session.createQuery("select count(*) "
				+ "from Fichaje f "
				+ "where f.fichajeExtra.usuario = 'fmateo' "
				//+ "and f.tipoFichaje = 'MANUAL'" //Se asume manual al tener fichaje extra
				+ "and f.empleado.id in (select e.id from EmpleadoPermanente e "
													+ "where e.cantidadHijos > 1)"
				);
		Long rta = (Long) query.uniqueResult();
		log.info("rta: " + rta);
		
		//Esto es para ver quien es fmateo jajaj
		Fichaje f = (Fichaje) session.get(Fichaje.class, new Long("20"));
		imprimirFichaje(f);
		
		//Imprimir empleados permanentes
		//Criteria crit = session.createCriteria(EmpleadoPermanente.class, "e")
		//		.add(Restrictions.ge("e.cantidadHijos", "1"));
		Query query2 = session.createQuery("select ep "
				+ "from EmpleadoPermanente ep "
				//+ "where ep.cantidadHijos > 0"
				);
		@SuppressWarnings("unchecked")
		List<EmpleadoPermanente> emplePerm= query2.list();
		
		log.info("Imprimo TODOS los empleados permanentes, para chequear los hijos");
		for(EmpleadoPermanente item: emplePerm) {
			log.info("id empleado permanente: "+ item.getId() + "     "
					+ "cantidad hijos: " + item.getCantidadHijos() + "     "
					+ "nombre: " + item.getNombre());
		}
		log.info("");
		log.info("************** FIN - TEST 9 *******************");

	}
	
	//@Test
	public void test10() {
		log.info("");
		log.info("************** INICIO TEST 10 - HQL/Criteria *******************");
		//10.	Indique el nombre y apellidos de los empleados que  han realizado fichajes 
		//en sucursales en las que no se encuentran habilitados?
		Query query = session.createQuery("select e from Empleado e "
				+ "where e.id in (select f.empleado.id "
				+ "from Fichaje f "
				+ "where f.empleado.id not in (select ehab.id from Sucursal s "
											+ "join s.empleadosHabilitados ehab "
											+ "where s.id = f.sucursal.id) "
				+ "group by f.empleado.id)"
				);
		
		@SuppressWarnings("unchecked")
		List<Empleado> rta = query.list();
		for(Empleado item: rta) {
			log.info("id: " + item.getId()
					+ "    nombre: " + item.getNombre()
					+ "    apellido: " + item.getApellido()
					);
		}
		
		//Prints para corroborar
		/*Query query2 = session.createQuery("select f from Fichaje f "
		+ "where f.empleado.id not in (select ehab.id from Sucursal s "
									+ "join s.empleadosHabilitados ehab "
									+ "where s.id = f.sucursal.id) "
		);
		List<Fichaje> fich = query2.list();
		for(Fichaje item: fich) {
			log.info("Fichaje que empleado no debia entrar en sucursal. ID = " + item.getId());
			log.info("Empleado id: " + item.getEmpleado().getId());
			log.info("sucursal id: " + item.getSucursal().getId());
			List<Empleado> ehab = (List<Empleado>) item.getSucursal().getEmpleadosHabilitados();
			for(Empleado e: ehab) {
				log.info("    id empleado habilitado: " + e.getId());
			}
			log.info("-----");
		}
		
		log.info("----Saltear-----------------------------------------");
		Query query3 = session.createQuery("select s from Sucursal s ");
				List<Sucursal> suc = query3.list();
				for(Sucursal item: suc) {
					log.info("Sucursal ID = " + item.getId());
					List<Empleado> ehab = (List<Empleado>) item.getEmpleadosHabilitados();
					for(Empleado e: ehab) {
						log.info("    id empleado habilitado: " + e.getId());
					}
					log.info("-----");
				}
		*/
		//Devuelve id de empleados habilitados de una sucursal
		//Query query2 = session.createQuery("select ehab.id from Sucursal s join s.empleadosHabilitados ehab where s.id=2");
		//List<Long> rta2 = query2.list();
		//for(Long item: rta2)
		//	log.info("sucursal emple id: " + item);
		
		
		log.info("");
		log.info("************** FIN - TEST 10 *******************");
		
	}
	
	//@Test
	public void test11() {
		log.info("");
		log.info("************** INICIO TEST 11 - HQL/Criteria *******************");
		//11.	Muestre la información de los fichajes realizados en la sucursal central donde 
		//la diferencia entre egresos e ingresos supera las 6 horas.
		Query query = session.createQuery("select f.id from Fichaje f "
				+ "where f.sucursal.central is true "
				+ "and (f.egreso-f.ingreso)*24*60 >= 360"		//Cambias 6 a 7 y parece que nadie labura mas de 7hs xD
				);
		@SuppressWarnings("unchecked")
		List<Long> rta2 = query.list();
		for(Long item: rta2) {
			log.info("Fichaje id: " + item);
			/*Query query2 = session.createQuery("select f from Fichaje f "
					+ "where f.id = :id"		//Cambias 6 a 7 y parece que nadie labura mas de 7hs xD
					)
					.setParameter("id", item);
			Fichaje f = (Fichaje) query2.uniqueResult();
			Long diff = f.getEgreso().getTime() - f.getIngreso().getTime();
			long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
			
			log.info("Fichaje id: " + item + "    diferencia minutos: " + diffInMinutes);
			log.info("Ingreso: " + f.getIngreso() + "     Egreso: " + f.getEgreso());*/
		}
		Query query2 = session.createQuery("select f from Fichaje f "
				+ "where f.id = :id"		//Cambias 6 a 7 y parece que nadie labura mas de 7hs xD
				)
				.setParameter("id", new Long("2"));
		Fichaje f = (Fichaje) query2.uniqueResult();
		Long diff = f.getEgreso().getTime() - f.getIngreso().getTime();
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		
		log.info("Fichaje id: 2" + "    diferencia minutos: " + diffInMinutes);
		log.info("Ingreso: " + f.getIngreso() + "     Egreso: " + f.getEgreso());
		
		
		
		log.info("");
		log.info("************** FIN - TEST 11 *******************");
		
	}
	
	//@Test
	public void test2_1() {
		log.info("");
		log.info("************** INICIO TEST 2_1 - HQL/Criteria *******************");
		//1.	Utilice hibernate para guardar objetos de cada una de las clases. 
		//		Para el caso de producto, programe un algoritmo que cree 50.000 productos de manera iterativa.
		//Ingrese el total de productos a cargar
		int totalProductos = 100;
		
		Medida m = new Medida();
		int min = 0, max = 100;
		m.setLargo(ThreadLocalRandom.current().nextDouble(min, max));
		m.setAncho(ThreadLocalRandom.current().nextDouble(min, max));
		m.setAltura(ThreadLocalRandom.current().nextDouble(min, max));
		
		Familia fam = new Familia();
		fam.setClave("101");
		fam.setDescripcion("Productos de acero");
		@SuppressWarnings("unused")
		Long idFam = (Long) session.save(fam); //Me devuelve su id
		
		TipoProducto tipo = new TipoProducto();
		tipo.setDescripcion("Un tipo de producto");
		@SuppressWarnings("unused")
		Long idTipo = (Long) session.save(tipo);
		
		//Creación de 50mil productos
		for(int i=0; i<totalProductos; i++) {
			Producto p = new Producto();
			p.setMedida(m);
			
			p.setCodigo(String.format("%010d", i));
			//log.info(p.getCodigo());
			
			Random r = new Random();
			String[] listNombre = {"Zapatos", "Notebook", "Auto", "Reloj", "Vaso"};
			String[] listColor = {"Rojo", "verde", "azul", "amarillo", "celeste"};
			p.setDescripcion(listNombre[r.nextInt(listNombre.length)] + " " 
					+ listColor[r.nextInt(listColor.length)]);
			short sh = (short) ThreadLocalRandom.current().nextInt(0, 10);
			p.setProductosPorPaquete(sh);
			p.setFamilia(fam);
			p.setTipoProducto(tipo);
			p.setInferior(r.nextBoolean());
			
			@SuppressWarnings("unused")
			Long idProducto = (Long) session.save(p);
			if(i % 1000 == 0)
				log.info("Ya se guardaron " + i + " productos");
		}

		
		//for(int i=0; i<1000; i++)
		//	session.save(new Producto());
		Query query = session.createQuery("select count(*) "
				+ "from Producto p"
				);
		Long totalproductos = (Long) query.uniqueResult();
		
		Producto pr = (Producto) session.get(Producto.class, new Long("1"));
		//log.info("Producto descripcion del id1: " + pr.getDescripcion());
		log.info("Total productos: " + totalproductos);
		ImpresoraDeModels.getInstancia().imprimirProducto(pr);
		log.info("");
		log.info("************** FIN - TEST 2_1  *******************");
		
	}
	
	//@Test
	public void test2_stockprint() {
		ImpresoraDeModels impresora = ImpresoraDeModels.getInstancia();
		
		Stock s = (Stock) session.get(Stock.class, new Long("1"));
		Maquina m = (Maquina) session.get(Maquina.class, new Long("1"));
		Producto p = (Producto) session.get(Producto.class, new Long("1"));
		Long totalPuesto = (Long) session.createQuery("select count(*) from Puesto s").uniqueResult();
		log.info("Total puesto: " + totalPuesto);
		impresora.imprimirStock(s);
		impresora.imprimirProducto(p);
	}
	
	

}
