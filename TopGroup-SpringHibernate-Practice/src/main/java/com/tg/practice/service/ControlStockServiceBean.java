package com.tg.practice.service;

import org.springframework.stereotype.Service;

import com.tg.practice.DAOInterface.*;
import com.tg.practice.model.*;

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
	public void actualizarStock(Long idProducto, Long stockActual) {
		//StockDAO stockDAO = ServiceLocator.stockDao();
		Stock s = stockDAO.buscarStockPorProducto(idProducto);
		s.setCantidad(stockActual);
		stockDAO.modificar(s);
	}
	
	public List<Pedido> findPedidosConStock() {
		return pedidoDAO.getSomePedidosConStock(cantidadMaximaImpresion);
	}
	
	public List<String> imprimirStocks(Locale locale) {
		return null;
	}
	
	
	
	
	
	
	
}
