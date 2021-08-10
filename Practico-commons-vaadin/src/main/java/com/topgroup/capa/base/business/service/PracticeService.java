package com.topgroup.capa.base.business.service;

import java.util.List;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.domain.model.Provincia;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.topgroup.capa.base.view.bean.ProductoViewBean;

public interface PracticeService {

	List<Producto> filter(ProductoFilter filter);

	List<Producto> findAll();
	
	List<TipoProducto> findAllTipoProductos();

	List<TipoProducto> findTipoProductosByProvincia(long idProvincia);
	
	List<Provincia> findAllProvincias();

	long count(ProductoFilter filter);

	void save(Producto bean);

	void save(ProductoViewBean bean);

}