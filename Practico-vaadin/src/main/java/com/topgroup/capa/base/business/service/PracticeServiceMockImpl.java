package com.topgroup.capa.base.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;

public class PracticeServiceMockImpl implements PracticeService  {

	private List<Producto> productos = null;
	List<TipoProducto> tipos = null;

	/* (non-Javadoc)
	 * @see com.topgroup.capa.base.business.service.PracticeService#filter(com.topgroup.commons.utils.lang.BaseFilter)
	 */
	@Override
	public List<Producto> filter(ProductoFilter filter) {
		ProductoFilter pFilter = (ProductoFilter) filter;
		List<Producto> allProductos = findAll();

		List<Producto> productos = new ArrayList<>();
		for (Producto p : allProductos) {
			if ((pFilter.getCodigo() == null || p.getCodigo().startsWith(
					pFilter.getCodigo()))
					&& (pFilter.getIdTipoProducto() == null || p
							.getTipoProducto().getId()
							.equals(pFilter.getIdTipoProducto()))) {
				productos.add(p);
			}
		}

		return productos;
	}

	/* (non-Javadoc)
	 * @see com.topgroup.capa.base.business.service.PracticeService#findAll()
	 */
	@Override
	public List<Producto> findAll() {
		if (productos == null) {
			productos = new ArrayList<>();

			List<TipoProducto> tipos = findAllTipoProductos();

			for (long i = 1; i < 100; i++) {
				Producto prod = new Producto();
				prod.setId(i);
				prod.setCodigo("Prod" + i);
				prod.setDescripcion("Producto destinado " + i);
				prod.setTipoProducto(i % 2 == 0 ? tipos.get(0) : tipos.get(1));
				short s = (short) new Random().nextInt(1 << 15); // any non-negative short
				prod.setProductosPorPaquete(s);
				productos.add(prod);

			}
		}
		return productos;
	}

	/* (non-Javadoc)
	 * @see com.topgroup.capa.base.business.service.PracticeService#findAllTipoProductos()
	 */
	@Override
	public List<TipoProducto> findAllTipoProductos() {
	//	if (tipos == null) {
			tipos = new ArrayList<>();

			for (long i = 1; i < 10; i++) {
				TipoProducto tp1 = new TipoProducto();
				tp1.setId(i);
				tp1.setDescripcion("Tipo de producto " + i);
				tipos.add(tp1);

			}
		//}
		return tipos;
	}

	/* (non-Javadoc)
	 * @see com.topgroup.capa.base.business.service.PracticeService#count(com.topgroup.commons.utils.lang.BaseFilter)
	 */
	@Override
	public long count(ProductoFilter filter) {
		return filter(filter).size();
	}

	/* (non-Javadoc)
	 * @see com.topgroup.capa.base.business.service.PracticeService#save(com.topgroup.capa.base.view.bean.ProductoViewBean)
	 */
	@Override
	public void save(Producto bean) {
		List<Producto> prod = findAll();
		if (!prod.contains(bean)) {
			prod.add(bean);
		}else{
			prod.remove(bean);
			prod.add(bean);
		}
	}

}
