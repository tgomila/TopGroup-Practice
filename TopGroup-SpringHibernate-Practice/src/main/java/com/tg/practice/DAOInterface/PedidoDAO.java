package com.tg.practice.DAOInterface;

import java.util.List;

import com.tg.practice.model.Pedido;

public interface PedidoDAO extends BasicDaoInterface<Pedido>{
	
	List<Pedido> getSomePedidosConStock(Long cantidadMaximaImpresion);
}
