package com.tg.practice.DAOInterface;

import java.util.List;

import com.tg.practice.model.Producto;

public interface ProductoDAO extends BasicDaoInterface<Producto>{
	
	List<Producto> getAllComplete();
	
	/*List<Producto> findAllProductos();

    Long altaProducto(Producto producto);

    void bajaProducto(Long productoId);

    void modificarProducto(Producto producto);

    Producto buscarProducto(Integer productoId);*/
}
