package com.topgroup.capa.base.business.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.domain.model.Provincia;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;

public class PracticeServiceTest {

	private static PracticeService service = new PracticeServiceMockImpl();

	@Test
	public void findTipoProductosByProvinciaTest() {

		for (Provincia provincia : service.findAllProvincias()) {
			List<TipoProducto> tipoProductos = service
					.findTipoProductosByProvincia(provincia.getId());
			Assert.assertEquals(2L, tipoProductos.size());
		}

	}

	@Test
	public void findProductos() {
		ProductoFilter filter = new ProductoFilter();

		for (Provincia provincia : service.findAllProvincias()) {
			for (TipoProducto tipoProducto : service
					.findTipoProductosByProvincia(provincia.getId())) {
				filter.setTipoProducto(tipoProducto);
				List<Producto> productos = service.filter(filter);
				Assert.assertEquals(10L, productos.size());
			}
		}

	}
}
