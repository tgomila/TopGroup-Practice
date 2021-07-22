package com.topgroup.capa.base.business.service;

import java.util.List;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;

public interface PracticeService {

	List<Producto> filter(ProductoFilter filter);

	List<Producto> findAll();

	List<TipoProducto> findAllTipoProductos();

	long count(ProductoFilter filter);

	void save(Producto bean);

}