package com.tg.practice.test;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tg.practice.model.Maquina;
import com.tg.practice.model.Pedido;
import com.tg.practice.model.Producto;
import com.tg.practice.model.Stock;
import com.tg.practice.model2.Fichaje;

public class ImpresoraDeModels {
	private static Logger log = LoggerFactory.getLogger(ImpresoraDeModels.class);
	
	private static ImpresoraDeModels instancia = null;
	
	public static ImpresoraDeModels getInstancia() {
		if (instancia == null) {
			instancia = new ImpresoraDeModels();
		}
		return instancia;
	}
	
	
	//Model 1
	
	//Inicio Stocks
	public void imprimirStocks(List<Stock> stocks) {
		if(stocks!=null && stocks.size() > 0) {
			log.info("Total stocks: " + stocks.size());
			for(Stock item: stocks)
				imprimirStock(item);
			
		}
		else
			log.info("No hay stocks");
	}	
	
	public void imprimirStock(Stock s) {
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"data-layer-context.xml", "applicationContext.xml", "db-context.xml", "service-layer-context.xml"});
		//SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
		//Session session = sf.openSession();
		
		if(s!=null) {
			log.info("id: "+s.getId());
			log.info("Codigo interno: " + s.getCodigoInterno());
			log.info("Token: " + s.getToken());
			log.info("Fecha stock: " + s.getFechaStock());
			log.info("Cantidad: " + s.getCantidad());
			log.info("Nota: " + s.getNota());
			if(s.getProducto() != null) {
				log.info("  producto del stock (id): " + s.getProducto().getId());
				log.info("  producto del stock (descripcion): " + s.getProducto().getDescripcion());
			}
			else
				log.info("No hay producto asignado");
			log.info("---------------------------------");
		}
		
	}
	//Fin stocks
	
	
	
	//Inicio Productos
	public void imprimirProductos(List<Producto> productos) {
		if(productos!=null && productos.size() > 0) {
			log.info("Total productos: " + productos.size());
			for(Producto item: productos)
				imprimirProducto(item);
			
		}
		else
			log.info("No hay productos");
	}	
	
	public void imprimirProducto(Producto p) {
		if(p!=null) {
			log.info("id: "+p.getId());
			if(p.getMedida()!=null) {
				log.info("   Medida (Largo): " + p.getMedida().getLargo());
				log.info("   Medida (Ancho): " + p.getMedida().getAncho());
				log.info("   Medida (Altura): " + p.getMedida().getAltura());
			}
			else
				log.info(" -- No hay medidas en producto");
			log.info("Codigo: " + p.getCodigo());
			log.info("Descripción: " + p.getDescripcion());
			log.info("Productos por paquete: " + p.getProductosPorPaquete());
			if(p.getFamilia()!=null) {
				log.info("   Familia (id): " + p.getFamilia().getId());
				log.info("   Familia (clave): " + p.getFamilia().getClave());
				log.info("   Familia (descripción): " + p.getFamilia().getDescripcion());
			}
			else
				log.info(" -- No hay familia en producto");
			if(p.getTipoProducto()!=null) {
				log.info("   TipoProducto (id): " + p.getTipoProducto().getId());
				log.info("   TipoProducto (descripción): " + p.getTipoProducto().getDescripcion());
			}
			else
				log.info(" -- No hay TipoProducto en producto");
			log.info("Es inferior (True/False)? = " + p.getInferior());
			
			//imprimo maquinas
			if(p.getMaquinas()!=null && p.getMaquinas().size() > 0) {
				log.info("Maquinas: " + p.getMaquinas().size());
				for(Maquina item: p.getMaquinas()) {
					log.info("   Maquina (id): " + item.getId());
					log.info("   Maquina (nombre): " + item.getNombre());
				}
			}
			else
				log.info("No hay maquinas");
			
			if(p.getStock()!=null) {
				log.info("Hay stock en producto:");
				log.info("   Familia (id): " + p.getStock().getId());
				log.info("   Familia (codigoInterno): " + p.getStock().getCodigoInterno());
				log.info("   Familia (nota): " + p.getStock().getNota());
			}
			else
				log.info(" -- No hay stock en producto");
			log.info("---------------------------------");
		}
		
	}
	//Fin productos
	
	
	//Inicio Pedidos
	public void imprimirPedidos(List<Pedido> pedidos) {
		if(pedidos!=null && pedidos.size() > 0) {
			log.info("Total pedidos: " + pedidos.size());
			for(Pedido item: pedidos)
				imprimirPedido(item);
			
		}
		else
			log.info("No hay productos");
	}	
	
	public void imprimirPedido(Pedido p) {
		if(p!=null) {
			log.info("id: "+p.getId());
			log.info("NroGuia: " + p.getNroGuia());
			log.info("Fecha de entrega: " + p.getFechaEntrega());
			log.info("Cantidad: " + p.getCantidad());
			log.info("Estado pedido: " + p.getEstadoPedido().toString());
			
			log.info("Producto:");
			if(p.getProducto()!=null) {
				log.info("   Producto (codigo): " + p.getProducto().getCodigo());
				log.info("   Producto (Descripción): " + p.getProducto().getDescripcion());
				log.info("   Producto (Productos por paquete): " + p.getProducto().getProductosPorPaquete());
			}
			else
				log.info(" -- No hay producto en pedido");
			
			log.info("---------------------------------");
		}
		
	}
	//Fin productos
	
	
	
	
	
	
	
	
	
	//---------------------   Model 2   -------------------------------------------------------
	
	//Fichajes
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
}
