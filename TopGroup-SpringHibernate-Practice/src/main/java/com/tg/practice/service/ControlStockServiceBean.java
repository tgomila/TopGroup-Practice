package com.tg.practice.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.tg.practice.DAOImpl.StockDAOImpl;
import com.tg.practice.DAOInterface.*;
import com.tg.practice.model.*;
import com.tg.practice.serviceInterface.ControlStockServiceInterface;
import com.tg.practice.serviceInterface.MessageFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ControlStockServiceBean implements ControlStockServiceInterface{
	
	private PedidoDAO pedidoDAO;
	private ProductoDAO productoDAO;
	private StockDAOImpl daoStock;// = ServiceLocator.stockDao();
	private List<MessageFormatter> cadenaDeFormatters = new ArrayList();
	private LanguageFormatter lf;
	private SignatureFormatter sf;
	private Long cantidadMaximaImpresion;
	
	private static ClassPathXmlApplicationContext context;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ControlStockServiceBean.class);
	
	//Metodos
	/**
	 * Actualiza la cantidad del stock de un producto dado adicionando una cierta cantidad
	 * de stock
	 * @param idProducto Identificador del producto para el cuál se desea
	 * actualizar el stock
	 * @param cantidad Cantidad que se debe adicionar al stock del producto
	 */
	public void actualizarStock(Long idProducto, Long stockActual) {
		//StockDAO stockDAO = ServiceLocator.stockDao();
		Stock s = daoStock.buscarStockPorProducto(idProducto);
		s.setCantidad(s.getCantidad()+stockActual);
		daoStock.modificar(s);
	}
	
	/**
	 *  Busca los pedidos que pueden ser cubiertos con la cantidad de stock actual y
	 *  envía un mail al encargado de preparación de envíos con el listado de los pedidos
	 *  con los siguientes datos de cada pedido: Nombre del producto, Cantidad del pedido,
	 *  Nro de guía
	 */
	public List<Pedido> findPedidosConStock() {
		return pedidoDAO.getSomePedidosConStock(cantidadMaximaImpresion);
		//Enviar mail a mateofacu@gmail.com
		//enviarMail(
		
	}
	
	/*
	 * Retorna una lista de mensajes informando por cada elemento de la misma
	 *  el nombre de un  producto y el stock en existencia para el mismo (en kilogramos) 
	 *  Se  deben tener en cuenta  todos  los stocks mayores que 0.
	 *  Cada uno de los mensajes  debe contener al final una firma provista por la 
	 *  empresa + la suma caractéres del nombre y el stock
	 *  La cantidad de mensajes debe estar limitada debido a la gran cantidad de productos
	 *  que vende la empresa
	 *  La impresión debe realizarse en el idioma solicitado
	 *	
	 *  Ejemplo de impresión en español
	 *   Nombre del producto : "Sugus"  Existencias: 800  kgs - ACCGE8
	 *   Nombre del producto : "Pico dulce"  Existencias: 356  kgs - ACCGE13
	 *   ............................................................
	 *  Ejemplo de impresión en inglés
	 *   Product name : "Sugus"  Stock: 800  kgs  - ACCGE8
	 *   Product name : "Pico dulce"  Stock: 356  kgs - ACCGE13
	 *   ............................................................
	 *
	 */
	public List<String> imprimirStocks(Locale locale) {
		context = new ClassPathXmlApplicationContext(new String[] {"data-layer-context.xml", "applicationContext.xml", "db-context.xml", "service-layer-context.xml"});
		StockDAO stockDAO = (StockDAO) context.getBean("stockDAO");
		List<Stock> stocks = stockDAO.getAll();//daoStock.getAll();
		List<String> stringList = new ArrayList<String>();
		
		LanguageFormatter lfAuxx = lf; 
		SignatureFormatter sfAuxx = sf;
		String msg = lfAuxx.format("test", new Object[] {"Batata", new Long(15)}, locale);
		System.out.println(sfAuxx.format(msg, new Object[] {"Batata", new Long(15)}, locale));
		
		for(Stock s : stocks) {
			//LanguageFormatter lf = (LanguageFormatter) cadenaDeFormatters.get(0);
			//SignatureFormatter sf = (SignatureFormatter) cadenaDeFormatters.get(1);
			LanguageFormatter lfAux = lf; 
			SignatureFormatter sfAux = sf;
			
			imprimirStock(s);
			if(locale == null)
				System.out.println("NUKK");
			else
				System.out.println("Hay objeto");
			String mensaje = lfAux.format("message", new Object[] {s.getProducto().getDescripcion(), s.getCantidad()}, locale);
			
			
			@SuppressWarnings("static-access")
			String mensajeConIdioma = mensaje.format(mensaje, new Object[] {s.getProducto().getDescripcion(), s.getCantidad()}, locale);
			stringList.add(mensajeConIdioma);
			System.out.println(mensajeConIdioma);
		}
		return stringList;
		
	}
	
	public void imprimirStocks(List<Stock> stocks) {
		if(stocks!=null && stocks.size() > 0) {
			System.out.println("Total stocks: " + stocks.size());
			for(Stock item: stocks)
				imprimirStock(item);
			
		}
		else
			System.out.println("No hay stocks");
	}	
	
	public void imprimirStock(Stock s) {
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"data-layer-context.xml", "applicationContext.xml", "db-context.xml", "service-layer-context.xml"});
		//SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
		//Session session = sf.openSession();
		
		if(s!=null) {
			System.out.println("id: "+s.getId());
			System.out.println("Codigo interno: " + s.getCodigoInterno());
			System.out.println("Token: " + s.getToken());
			System.out.println("Fecha stock: " + s.getFechaStock());
			System.out.println("Cantidad: " + s.getCantidad());
			System.out.println("Nota: " + s.getNota());
			if(s.getProducto() != null) {
				System.out.println("  producto del stock (id): " + s.getProducto().getId());
				System.out.println("  producto del stock (descripcion): " + s.getProducto().getDescripcion());
			}
			else
				System.out.println("No hay producto asignado");
			System.out.println("---------------------------------");
		}
		
	}
	//Fin stocks
	
	public String getOneStringPedidos(List<Pedido> pedidos) {
		String s = new String();
		for(Pedido p: pedidos) {
			s = s + "NroGuia: " + p.getNroGuia() + ",/t Fecha de entrega: " + p.getFechaEntrega().toString() + "\n";
			s = s + "CodigoProducto: " + p.getProducto().getCodigo() + ",\t Cantidad: " + p.getCantidad() + 
					",\t Descripcion: " + p.getProducto().getDescripcion() + "\n";
			s = s + "----------------------------------------------\n";
		}
		return s;
		
	}
	
	public void enviarMail(List<String> datos, String emailDestino) {
		//Enviar mail
	}

	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public StockDAOImpl getDaoStock() {
		return daoStock;
	}

	public void setDaoStock(StockDAOImpl daoStock) {
		this.daoStock = daoStock;
	}

	public List<MessageFormatter> getCadenaDeFormatters() {
		return cadenaDeFormatters;
	}

	public void setCadenaDeFormatters(List<MessageFormatter> cadenaDeFormatters) {
		this.cadenaDeFormatters = cadenaDeFormatters;
	}

	public Long getCantidadMaximaImpresion() {
		return cantidadMaximaImpresion;
	}

	public void setCantidadMaximaImpresion(Long cantidadMaximaImpresion) {
		this.cantidadMaximaImpresion = cantidadMaximaImpresion;
	}

	public LanguageFormatter getLf() {
		return lf;
	}

	public void setLf(LanguageFormatter lf) {
		this.lf = lf;
	}

	public SignatureFormatter getSf() {
		return sf;
	}

	public void setSf(SignatureFormatter sf) {
		this.sf = sf;
	}
	
	
	
	
	
}
