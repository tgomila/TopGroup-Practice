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
	private static final Object[] VISIBLE_COLUMNS = new String[] {"codigo", "descripcion","tipoProducto.descripcion"};
	
	//Me genera errores por el getMessage
	//La solución es agregar al message.properties al parecer
	private static final String[] COLUMN_HEADERS = new String[] { VaadinUtil.getMessage("ProductoViewBean.codigo"),
			VaadinUtil.getMessage("ProductoViewBean.descripcion"), VaadinUtil.getMessage("ProductoViewBean.fechaAlta")};
	
	//Esto esta en ProductoFormPanel también
	//private static final String[] VISIBLE_ITEMS = new String[] {"codigo", "tipoProducto","fechaAlta"};
	private static final String[] VISIBLE_ITEMS = new String[] {VaadinUtil.getMessage("ProductoViewBean.codigo"),
			VaadinUtil.getMessage("ProductoViewBean.tipoProducto"), VaadinUtil.getMessage("ProductoViewBean.fechaAlta")};
	
	@Autowired
	private ProductoViewBean productoViewBean;
	
	@Autowired
	private ProductoFilter productoFilter;
	
	@Autowired
	private ProductoFormPanel productoFormPanel;
	
	@Autowired
	private ProductoContainer productoContainer;
	
	@Autowired
	private ProductoEditScreen productoEditScreen;
	private static Boolean editaEnVentanaMaximizada = true;
	private static Boolean editaEnVentanaPopup = true;

	@Override
	protected ProductoViewBean getBeanForm() {
		System.out.println("Entre a getBeanForm");
		return productoViewBean;
	}

	@Override
	protected String[] getColumnHeaders() {
		System.out.println("Entre a ProductoSearchPanel.columnHeaders");
		return productoFormPanel.getVisibleItemProperties();
		//return COLUMN_HEADERS;
	}

	@Override
	protected Container getContainerDataSource() {
		return productoContainer.getAllProductos();
	}

	@Override
	protected Object[] getVisibleColumns() {
		//return productoContainer.getVisibleColumns();
		System.out.println("Entre a ProductoSearchPanel.getVisibleColumns");
		return VISIBLE_COLUMNS;
	}

	@Override
	protected String[] getVisibleItemProperties() {
		System.out.println("Entre a ProductoSearchPanel.getVisibleItemProperties");
		//return COLUMN_HEADERS;
		//return new String[] {"codigo", "descripcion","fechaAlta"};
		return VISIBLE_ITEMS;
	}
	
	//Cuando apretas el botón "nuevo". Hay que abrir un pop up de edición.
	@Override
	protected void newEntity(ClickEvent event) {
		System.out.println("Entre a ProductoSearchPanel.newEntity");
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
		System.out.println("Entre a ProductoSearchPanel.valueChange");
	}
	
	/*
	 * Revisar el filter
	 */
	@Override
	public void search(ClickEvent event) {
		System.out.println("Entre a ProductoSearchPanel.search");
		
		//Este NO anda
		//ProductoFilter f = (ProductoFilter) event.getComponent();
		
		System.out.println("Search 1");
		ProductoViewBean bean = form.getBeanItem().getBean();
		System.out.println("Search 2");
		System.out.println("ProductoBean: codigo: " + bean.getCodigo()+
				". Descripcion: " + bean.getDescripcion()+
				". fechaAlta: "+bean.getFechaAlta());
		ProductoFilter filter = getFilterValues(bean);
		System.out.println("Search 3");
		System.out.println("ProductoFilter: codigo: " + filter.getCodigo()+
				". Tipo producto: " + filter.getTipoProducto().getDescripcion()+
				". fechaAlta: "+bean.getFechaAlta());
		productoContainer.cargarTablaResultadosConFiltro(filter);
		System.out.println("Search 4");
		reloadTableDataSource(productoContainer.getProductos());//Cargar tabla con datos de consulta.
		System.out.println("Search 6");
	}

	private ProductoFilter getFilterValues(ProductoViewBean bean) {
		System.out.println("Entre a ProductoSearchPanel.getFilterValues");
		ProductoFilter filter = new ProductoFilter();
		filter.setCodigo(bean.getCodigo());
		TipoProducto tp = new TipoProducto();
		tp.setId(new Long(-1));
		tp.setDescripcion("TipoProductoNotOnProductoView");
		filter.setTipoProducto(tp);
		
		return filter;
	}

}
