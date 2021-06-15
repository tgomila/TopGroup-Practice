package com.tg.practice.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tg.practice.DAOImpl.*;
import com.tg.practice.DAOInterface.*;
import com.tg.practice.model.*;
import com.tg.practice.model2.*;
import com.tg.practice.service.ServiceLocator;

public class CargarDatos {
	
	private static Logger log = LoggerFactory.getLogger(Spring_Practico1.class);
	private static CargarDatos instancia = null;
	
	public static CargarDatos getInstancia() {
		if (instancia == null) {
			instancia = new CargarDatos();
		}
		return instancia;
	}
	
	public void cargarProductos() {
		// Este test es para probar si funcionan los XML de spring, es básico.
		try {
			log.info("------------PAUSAAAAAAA 0--------------------------");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("");
		log.info("************** INICIO TEST 1 - Chequear empleados *******************");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"data-layer-context.xml", "applicationContext.xml", "db-context.xml", "service-layer-context.xml"});
		//ProductoDAO productoDAO = ServiceLocator.getSoleInstance().getProductoDAO();
		//StockDAO stockDAO = ServiceLocator.getSoleInstance().getStockDAO();
		//FamiliaDAO familiaDAO = ServiceLocator.getSoleInstance().getFamiliaDAO();
		//TipoProductoDAO tipoProductoDAO = ServiceLocator.getSoleInstance().getTipoProductoDAO();
		//MaquinaDAO maquinaDAO = ServiceLocator.getSoleInstance().getMaquinaDAO();
		
		//Traigo los DAO's
		ProductoDAO productoDAO = (ProductoDAOImpl) context.getBean("productoDAO");
		StockDAO stockDAO = (StockDAOImpl) context.getBean("stockDAO");
		FamiliaDAO familiaDAO = (FamiliaDAOImpl) context.getBean("familiaDAO");
		TipoProductoDAO tipoProductoDAO = (TipoProductoDAO) context.getBean("tipoProductoDAO");
		MaquinaDAO maquinaDAO = (MaquinaDAO) context.getBean("maquinaDAO");
		
		
		try {
			log.info("------------PAUSAAAAAAA 1--------------------------");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Creo 1 producto, 1 stock, 1 TipoProducto, 1 medida
		Producto prod;
		Stock stock;
		Pedido ped;
		//Misma medida para todos, porque si xD
		Medida m = new Medida(new Double(15), new Double(15), new Double(15));
		
		//Asigno id 1 para que solo exista 1 tipo producto
		TipoProducto t = new TipoProducto(null, "Un tipo de producto");
		//t = tipoProductoDAO.alta(t);
		Familia f = new Familia(null, "H", "Harinas");
		//f = familiaDAO.alta(f);
		Maquina maquina = new Maquina(null,  new ArrayList<Producto>());
		maquina = maquinaDAO.alta(maquina);
		try {
			log.info("------------PAUSAAAAAAA 2--------------------------");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Maquina> maquinas = new ArrayList<Maquina>();
		maquinas.add(maquina);
		
		//Producto 1 - 10 stock - 1 pedido con 5 
		//Doy de alta producto, sin stock
		prod = new Producto(new Long("1"), m, "001", "Fideos", (short) 5, f, t, false, maquinas, null);
		prod = productoDAO.alta(prod);
		
		//Doy de alta stock, le asigno su producto "prod".
		stock = new Stock(new Long("1"), new Long("1001"), "myToken", getDate("14/06/2021"), new Long("10"), "Notita de stock", prod);
		stock = stockDAO.alta(stock);
		try {
			log.info("------------PAUSAAAAAAA 3--------------------------");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Modifico "prod", le asigno su nuevo objeto stock "s"
		prod.setStock(stock);
		log.info("id: " + prod.getId());
		productoDAO.modificar(prod);
		try {
			log.info("------------PAUSAAAAAAA 4--------------------------");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Busco mi producto
		prod = productoDAO.buscar(new Long("1"));
		ImpresoraDeModels.getInstancia().imprimirProducto(prod);
		//Producto p1 = productoDAO.buscar(new Long(1));
		//ImpresoraDeModels.getInstancia().imprimirProducto(p1);
		List<Producto> productos = productoDAO.getAllComplete();
		ImpresoraDeModels.getInstancia().imprimirProductos(productos);
		//Producto 2 - 0 stock
		
		
		
		//Empleado emple = empleadoDAO.buscar(new Long("1"));
		//log.info("Empleado id 1, nombre: " + emple.getNombre());
		
		log.info("**************** FIN TEST 1- Chequear empleados  ********************");
	}
	
	public Date getDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try {			
			formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
}
