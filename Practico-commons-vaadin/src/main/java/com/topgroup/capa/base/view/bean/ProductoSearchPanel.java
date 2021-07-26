package com.topgroup.capa.base.view.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.domain.model.Provincia;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.topgroup.commons.vaadin.util.VaadinUtil;
import com.topgroup.commons.vaadin.view.panel.BaseFormPanel;
import com.topgroup.commons.vaadin.view.panel.BaseSearchPanel;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@Component()
@Scope("prototype")
public class ProductoSearchPanel extends BaseSearchPanel<ProductoViewBean>{
	
	private static final long serialVersionUID = 1L;
	private static final Object[] VISIBLE_COLUMNS = new String[] {"codigo", "descripcion","tipoProducto"};
	
	private static final String[] COLUMN_HEADERS = new String[] { VaadinUtil.getMessage("ProductoViewBean.codigo"),
			VaadinUtil.getMessage("ProductoViewBean.descripcion"), VaadinUtil.getMessage("ProductoViewBean.tipoProducto")};
	
	@Autowired
	private ProductoFormPanel productoFormPanel;
	
	private ProductoContainer productoContainer = new ProductoContainer();
	
	@Autowired
	private ProductoEditScreen productoEditScreen;
	private static Boolean editaEnVentanaMaximizada = true;
	private static Boolean editaEnVentanaPopup = true;

	@Override
	protected ProductoViewBean getBeanForm() {
		return new ProductoViewBean();
	}

	@Override
	protected String[] getColumnHeaders() {
		//return productoFormPanel.getVisibleItemProperties();
		return COLUMN_HEADERS;
	}

	@Override
	protected Container getContainerDataSource() {
		return productoContainer.getAllProductos();
	}

	@Override
	protected Object[] getVisibleColumns() {
		//return productoContainer.getVisibleColumns();
		return VISIBLE_COLUMNS;
	}

	@Override
	protected String[] getVisibleItemProperties() {
		return COLUMN_HEADERS;
	}
	
	//Cuando apretas el botón "nuevo". Hay que abrir un pop up de edición.
	@Override
	protected void newEntity(ClickEvent event) {
		ProductoViewBean bean = new ProductoViewBean();
		productoEditScreen.setModeForm(BaseFormPanel.MODE_INPUT_FORM);
		productoEditScreen.init(bean, productoEditScreen);
		productoEditScreen.setProductoSearchPanel(ProductoSearchPanel.this);
		Window window = productoEditScreen.createWindow();
		getApplication().getMainWindow().addWindow(window);
		if (editaEnVentanaMaximizada)
			window.setSizeFull();
	}
	
	//Este es cuando hacemos click a un coso de la tabla.
	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * Revisar el filter, porque provincia es null
	 */
	@Override
	public void search(ClickEvent event) {
		
		//Esto es cuando presionamos el botón search (boton búsqueda). Tomamos
		//los filtros del objeto "event", hacemos una llamada al servicio
		ProductoFilter f = (ProductoFilter) event.getComponent();
		ProductoViewBean bean = form.getBeanItem().getBean();
		ProductoFilter filter = getFilterValues(bean);
		productoContainer.cargarTablaResultados(f);
		reloadTableDataSource(productoContainer.getAllProductos());//Cargar tabla con datos de consulta.
	}

	private ProductoFilter getFilterValues(ProductoViewBean bean) {
		ProductoFilter filter = new ProductoFilter();
		filter.setCodigo(bean.getCodigo());
		filter.setFechaVencimiento(null);
		Provincia prov = new Provincia();
		prov.setId(new Long(-1));
		prov.setNombre("ProvinciaNotOnProductoView");
		filter.setProvincia(prov);
		TipoProducto tp = new TipoProducto();
		tp.setId(new Long(-1));
		tp.setDescripcion("TipoProductoNotOnProductoView");
		filter.setTipoProducto(tp);
		
		return filter;
	}

}
