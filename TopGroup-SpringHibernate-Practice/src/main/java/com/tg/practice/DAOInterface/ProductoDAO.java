package com.tg.practice.DAOInterface;

import java.util.List;

import com.tg.practice.model.Producto;

public interface ProductoDAO extends BasicDaoInterface<Producto>{
	
	List<Producto> getAllComplete();
}
