package com.tg.practice.DAOInterface;

import com.tg.practice.model.Stock;

public interface StockDAO  extends BasicDaoInterface<Stock>{
	
	Stock buscarStockPorProducto(Long idProducto);
}
