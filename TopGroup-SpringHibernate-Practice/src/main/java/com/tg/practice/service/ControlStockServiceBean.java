package com.tg.practice.service;

import org.springframework.stereotype.Service;

import com.tg.practice.DAOInterface.*;
import com.tg.practice.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ControlStockServiceBean {
	
	private PedidoDAO pedidoDAO;
	private ProductoDAO productoDAO;
	private StockDAO stockDAO;// = ServiceLocator.stockDao();
	
	private final Long cantidadMaximaImpresion = new Long("50");
	
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
		Stock s = stockDAO.buscarStockPorProducto(idProducto);
		s.setCantidad(s.getCantidad()+stockActual);
		stockDAO.modificar(s);
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
	
	public List<String> imprimirStocks(Locale locale) {
		return null;
	}
	
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
	
	
	
	
	
}
