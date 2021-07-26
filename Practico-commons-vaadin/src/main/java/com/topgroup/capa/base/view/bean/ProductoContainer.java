package com.topgroup.capa.base.view.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.business.service.PracticeService;
import com.topgroup.capa.base.business.service.PracticeServiceMockImpl;
import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

//@Component("productoContainer")
public class ProductoContainer {
	
	PracticeService practiceService = new PracticeServiceMockImpl();
	
	//Table table = new Table();
	BeanContainer<String, ProductoViewBean> beanContainer = new BeanContainer<String, ProductoViewBean>(ProductoViewBean.class);
	
	public ProductoContainer() {
		getAllProductos();
		beanContainer.setBeanIdProperty("Código");
		beanContainer.setBeanIdProperty("Descripción");
		beanContainer.setBeanIdProperty("Tipo de producto");
		System.out.println("Pase por constructor de ProductoContainer");
	}
	

	
	public Container getAllProductos() {
		cargarTablaResultados(null);
		return beanContainer;
	}
	
	

	
	public void cargarTablaResultados(ProductoFilter filter) {
		limpiarTabla();
		
		List<Producto> productos;
		if(filter == null)
			productos = practiceService.findAll();
		else
			productos = practiceService.filter(filter);
		
		for(Producto p: productos) {
			ProductoViewBean bean = new ProductoViewBean(p);
			beanContainer.addBean(bean);
		}
	}
	

	
	public void limpiarTabla() {
		beanContainer.removeAllItems();
	}
	
	public Object[] getColumnHeaders() {
		Collection<String> collectionVisibleColumns = beanContainer.getContainerPropertyIds();
		String[] stringArrayVisibleColumns = (String[]) collectionVisibleColumns.toArray();
		return stringArrayVisibleColumns;
	}
	
	public void guardar(ProductoViewBean bean) {
		
		practiceService.save(bean);
	}
	
	
	
	
	
	
	
	
	
	
	
	//Basurero:
	/*public Container getAllProductos() {
		
		table = new Table();
		table.addStyleName("components-inside");
		table.setWidth("700px");
		
		table.setColumnCollapsingAllowed(true);
		// Allow selecting items from the table.
		table.setSelectable(true);
		// Shows feedback from selection.
		final Label current = new Label("Selected: -");
		//mainLayout.addComponent(current);
		table.addListener(new Property.ValueChangeListener()
		{
			private static final long serialVersionUID = -5916153303179153638L;

			public void valueChange(ValueChangeEvent event)
			{
				current.setValue("Selected table id: " + table.getValue());
				//getWindow().showNotification("Current: "+current);
			}
		});

		// Define the names and data types of columns.
		// The "default value" parameter is meaningless here.
		table.addContainerProperty("Código",           Label.class,  null);
		table.addContainerProperty("Descripción",      Label.class,  null);
		table.addContainerProperty("Tipo de producto", Label.class,  null);
		table.addContainerProperty("Detalles", 		   Button.class, null);
		
		cargarTablaResultados(null);
		
		table.setPageLength(10);
		//mainLayout.addComponent(table);
		
		// Equivalent access through the container
		Container container = (Container) table.getContainerDataSource();
		return container;
	}
	
	public void cargarTablaResultados(ProductoFilter filter) {
		limpiarTabla();
		
		List<Producto> productos;
		if(filter == null)
			productos = practiceService.findAll();
		else
			productos = practiceService.filter(filter);
		
		int itemId = 0;
		for(Producto p: productos) {
			Label codigo = new Label(p.getCodigo());
			Label descripcion = new Label(p.getDescripcion());
			Label tipoDeProducto = new Label(p.getTipoProducto().getDescripcion());
			//codigo.setWidth(15.0f);
			
			Integer itemIdInteger = new Integer(itemId);
			Button detailsField = new Button("Mostrar detalle");
			detailsField.addStyleName("link");
			detailsField.setData(itemIdInteger);
			detailsField.addListener(new Button.ClickListener() {
				
				private static final long serialVersionUID = 6544360791415955833L;

				public void buttonClick(ClickEvent event) {
					// Get the item identifier from the user-defined data.
					//Integer itemIdInteger = (Integer)event.getButton().getData();
					//getWindow().showNotification("Link "+itemIdInteger.intValue());
				}
			});
			
			table.addItem(new Object[] {codigo, descripcion, tipoDeProducto, detailsField}, itemId);
			itemId++;
		}
	}
	
	public void limpiarTabla() {
		table.getContainerDataSource().removeAllItems();
	}
	
	public Object[] getVisibleColumns() {
		return table.getVisibleColumns();
	}
	
	public String[] getVisibleItemProperties() {
		return table.getColumnHeaders();
	}
	*/
	
}
