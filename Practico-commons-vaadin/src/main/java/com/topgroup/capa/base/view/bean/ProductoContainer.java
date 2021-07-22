package com.topgroup.capa.base.view.bean;

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
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

//@Component("productoContainer")
public class ProductoContainer {
	
	PracticeService practiceService = new PracticeServiceMockImpl();
	
	Table table = new Table();
	
	public ProductoContainer() {
		getAllProductos();
	}
	
	public Container getAllProductos() {
		
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
			/**
			 * 
			 */
			private static final long serialVersionUID = -5916153303179153638L;

			public void valueChange(ValueChangeEvent event)
			{
				current.setValue("Selected table id: " + table.getValue());
				//getWindow().showNotification("Current: "+current);
			}
		});

		/* Define the names and data types of columns.
		 * The "default value" parameter is meaningless here. */
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
	
	public Container getAllProductos2() {
		BeanItemContainer<Producto> beans = new BeanItemContainer<Producto>(type)
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
				/**
				 * 
				 */
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
		/*table.removeAllItems();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List props = new ArrayList(table.getContainerPropertyIds());
		for(Object prop: props){
		   table.removeContainerProperty(prop);
		}*/
	}
	
	public Object[] getVisibleColumns() {
		return table.getVisibleColumns();
	}
	
	public String[] getVisibleItemProperties() {
		return table.getVisibleItemIds();
	}
}
