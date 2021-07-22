package com.topgroup.capa.base.view.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.topgroup.commons.vaadin.view.panel.BaseFormPanel;
import com.topgroup.commons.vaadin.view.panel.BaseSearchPanel;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;

@Component()
@Scope("prototype")
public class ProductoSearchPanel extends BaseSearchPanel<FiltroProducto>{
	
	private static final long serialVersionUID = -5367918639632299578L;
	
	@Autowired
	private ProductoFormPanel productoFormPanel;
	
	private ProductoContainer productoContainer = new ProductoContainer();

	@Override
	protected FiltroProducto getBeanForm() {
		return new FiltroProducto();
	}

	@Override
	protected String[] getColumnHeaders() {
		return productoFormPanel.getVisibleItemProperties();
	}

	@Override
	protected Container getContainerDataSource() {
		return productoContainer.getAllProductos();
	}

	@Override
	protected Object[] getVisibleColumns() {
		return productoContainer.getVisibleColumns();
	}

	@Override
	protected String[] getVisibleItemProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Cuando apretas el botón "nuevo". Hay que abrir un pop up de edición.
	@Override
	protected void newEntity(ClickEvent arg0) {
		// TODO Auto-generated method stub
		ProductoFilter bean = new ProductoFilter();
		productoFormPanel.setModeForm(BaseFormPanel.MODE_INPUT_FORM);
		//productoFormPanel.init(bean, callbackForm);
		getApplication().getMainWindow().addWindow(
		productoFormPanel.createWindow());
	}
	
	//Este es cuando hacemos click a un coso de la tabla.
	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void search(ClickEvent event) {
		
		//Esto es cuando presionamos el botón search (boton búsqueda). Tomamos
		//los filtros del objeto "event", hacemos una llamada al servicio
		
		//Sera asi?
		ProductoFilter f = (ProductoFilter) event.getComponent();
		productoContainer.cargarTablaResultados(f);
		
		//reloadTableDataSource(table);//Cargar tabla con datos de consulta.
	}

}
