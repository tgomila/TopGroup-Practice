package com.topgroup.capa.base.view.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.domain.model.Provincia;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.topgroup.commons.vaadin.field.DataCombobox;
import com.topgroup.commons.vaadin.util.VaadinUtil;
import com.topgroup.commons.vaadin.view.form.PanelBeanForm;
import com.topgroup.commons.vaadin.view.panel.BaseFormPanel;
import com.topgroup.commons.vaadin.view.panel.BaseSearchPanel;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;

@Component()
@Scope("prototype")
public class ProductoSearchPanel extends BaseSearchPanel<ProductoViewBean>{
	
	private static final long serialVersionUID = 1L;
	
	//Esto esta en ProductoFormPanel también
	private static final String[] VISIBLE_ITEMS = new String[] {VaadinUtil.getMessage("ProductoViewBean.codigo"),
			VaadinUtil.getMessage("ProductoViewBean.tipoProducto"), VaadinUtil.getMessage("ProductoViewBean.fechaAlta")};
		
	//Resultados en la table
	private static final Object[] VISIBLE_COLUMNS = new String[] {"codigo", "descripcion","tipoProducto.descripcion"};
	
	//Me genera errores por el getMessage
	//La solución es agregar al message.properties al parecer
	private static final String[] COLUMN_HEADERS = new String[] { VaadinUtil.getMessage("ProductoRowViewBean.codigo"),
			VaadinUtil.getMessage("ProductoRowViewBean.descripcion"), VaadinUtil.getMessage("ProductoRowViewBean.tipoProducto")};
	
	
	@Autowired
	private ProductoViewBean productoViewBean;
	
	@Autowired
	private ProductoFilter productoFilter;
	
	@Autowired
	private ProductoFormPanel productoFormPanel;
	
	@Autowired
	private ProductoContainer productoContainer;
	
	@Autowired
	private ProductoTipoGenerator productoTipoGenerator;
	
	private DataCombobox<TipoProducto> tipoProductoCombobox;
	
	@Autowired
	private ProductoEditScreen productoEditScreen;
	private static Boolean editaEnVentanaMaximizada = false;
	private static Boolean editaEnVentanaPopup = true;

	@Override
	protected ProductoViewBean getBeanForm() {
		System.out.println("Entre a getBeanForm");
		return productoViewBean;
	}

	@Override
	protected String[] getColumnHeaders() {
		System.out.println("Entre a ProductoSearchPanel.getColumnHeaders");
		return COLUMN_HEADERS;
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
		return VISIBLE_ITEMS;
	}
	
	//Cuando apretas el botón "nuevo". Hay que abrir un pop up de edición.
	@Override
	protected void newEntity(ClickEvent event) {
		System.out.println("Entre a ProductoSearchPanel.newEntity");
		
		/* //La forma clásica con Form no funciono al dar de alta. Probando otro método.
		productoFormPanel.init(productoFilter, productoFormPanel);
		Window window = productoFormPanel.createWindow();		
		getApplication().getMainWindow().addWindow(window);
		*/
		
		//Este sí funciona
		ProductoViewBean bean = new ProductoViewBean();
		productoEditScreen.setModeForm(BaseFormPanel.MODE_INPUT_FORM);
		productoEditScreen.init(bean, productoEditScreen);
		productoEditScreen.setProductoSearchPanel(ProductoSearchPanel.this);
		Window window = productoEditScreen.createWindow();
		getApplication().getMainWindow().addWindow(window);
		if (editaEnVentanaMaximizada)
			window.setSizeFull();
		
		System.out.println("Sali de ProductoSearchPanel.newEntity");
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
		
		ProductoViewBean bean = form.getBeanItem().getBean();
		ProductoFilter filter = getFilterValues(bean);
		productoContainer.cargarTablaResultadosConFiltro(filter);
		reloadTableDataSource(productoContainer.getProductos());//Cargar tabla con datos de consulta.
	}

	private ProductoFilter getFilterValues(ProductoViewBean bean) {
		System.out.println("Entre a ProductoSearchPanel.getFilterValues");
		ProductoFilter filter = new ProductoFilter();
		filter.setCodigo(bean.getCodigo());
		filter.setTipoProducto(bean.getTipoProducto());
		filter.setFechaAlta(bean.getFechaAlta());
		
		return filter;
	}
	
	protected void addFieldGenerator(PanelBeanForm<ProductoViewBean> beanForm) {
		super.addFieldGenerator(beanForm);
		beanForm.addFieldGenerator(VISIBLE_ITEMS[1], productoTipoGenerator);
	}

}
