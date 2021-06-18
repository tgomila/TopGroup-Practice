package com.tg.practice.serviceInterface;

import java.util.List;
import java.util.Locale;

import com.tg.practice.model.Pedido;

public interface ControlStockServiceInterface {
	
	void actualizarStock(Long idProducto, Long stockActual);
	
	List<Pedido> findPedidosConStock();
	
	List<String> imprimirStocks(Locale locale);
}
