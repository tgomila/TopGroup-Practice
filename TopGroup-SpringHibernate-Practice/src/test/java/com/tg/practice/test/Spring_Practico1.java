package com.tg.practice.test;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tg.practice.DAOImpl.*;
import com.tg.practice.DAOInterface.*;
import com.tg.practice.model2.*;

public class Spring_Practico1 {
	private static Logger log = LoggerFactory.getLogger(Spring_Practico1.class);
	
	private static ClassPathXmlApplicationContext context;
	@BeforeClass
	public static void initialize() {
		context = new ClassPathXmlApplicationContext(new String[] {"data-layer-context.xml", "applicationContext.xml", "db-context.xml", "service-layer-context.xml"});
	}
	
	//@Test
	public void test1() {
		// Este test es para probar si funcionan los XML de spring, es básico.
		log.info("");
		log.info("************** INICIO TEST 1 - Chequear empleados *******************");
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"data-layer-context.xml", "applicationContext.xml", "db-context.xml", "service-layer-context.xml"});
		
		EmpleadoDAO empleadoDAO = (EmpleadoDAOImpl) context.getBean("empleadoDAO");
		//SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Empleado emple = empleadoDAO.buscar(new Long("1"));
		log.info("Empleado id 1, nombre: " + emple.getNombre());
		
		log.info("**************** FIN TEST 1- Chequear empleados  ********************");
	}
	
	@Test
	public void test2() {
		log.info("");
		log.info("************** INICIO TEST 2 - Chequear empleados *******************");
		CargarDatos.getInstancia().cargarProductosTest(context);
		
		
		log.info("**************** FIN TEST 2- Chequear empleados  ********************");
	}

}
