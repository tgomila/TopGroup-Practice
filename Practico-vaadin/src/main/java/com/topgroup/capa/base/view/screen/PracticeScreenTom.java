package com.topgroup.capa.base.view.screen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.ResolutionSyntax;

import org.springframework.beans.factory.annotation.Autowired;

import com.topgroup.capa.base.business.service.*;
import com.topgroup.capa.base.domain.model.Producto;
import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PracticeScreenTom extends CustomComponent {

	private static final long serialVersionUID = -4479639018984308437L;
	
	private VerticalLayout mainLayout;
		
	
	//Inicio Componentes
	@Autowired
	PracticeService practiceService = new PracticeServiceMockImpl();
	TextField fieldCodigo;
	Select selectTipoDeProducto;
	List<TipoProducto> tipoDeProductos;
	DateField date;	
	Table table = new Table();
	private Button buttBuscar, buttLimpiar, buttNuevo;
	//Fin componentes

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	public PracticeScreenTom() {
		buildMainLayout();
		agregarListenersABotones();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private void buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		Label label = new Label("Práctica 1 de Vaadin");
		label.addStyleName("title");
		mainLayout.addComponent(label);
		
		
		
		// Create a 4 by 4 grid layout.
		fieldCodigo = new TextField("Codigo");
		
		selectTipoDeProducto = new Select("Tipo de producto");
		tipoDeProductos = practiceService.findAllTipoProductos();
		for(TipoProducto t: tipoDeProductos)	//Agrego tipos de productos para seleccionar
			selectTipoDeProducto.addItem(t.getDescripcion());
		
		date = new DateField("Fecha de alta");// Create a DateField with the default style
		date.setValue(new Date());// Set the date and time to present
		date.setDateFormat("yyyy-MM-dd");// Set the prompt
		//date.setResolution(RESOLUTION_DAY);
		
		
		// Create a 4 by 4 grid layout.
		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setWidth("50%");
		
		FormLayout c1formLayout = new FormLayout();// Make the FormLayout shrink to its contents
		c1formLayout.setSizeUndefined();
		//gridLayout.addStyleName("Head");  //nose si va esto, es .css
		// Fill out the first row using the cursor.
		c1formLayout.addComponent(fieldCodigo);
		c1formLayout.addComponent(date);
		gridLayout.addComponent(c1formLayout);
		gridLayout.setColumnExpandRatio(0, 5);
		
		FormLayout c2formLayout = new FormLayout();// Make the FormLayout shrink to its contents
		c2formLayout.setSizeUndefined();
		c2formLayout.addComponent(selectTipoDeProducto);
		gridLayout.addComponent(c2formLayout);
		gridLayout.setColumnExpandRatio(1, 5);
		
		
		
		AbsoluteLayout layout = new AbsoluteLayout();
		layout.setWidth("400px");
		layout.setHeight("50px");
		
		buttBuscar = new Button("Buscar");
		buttLimpiar = new Button("Limpiar");
		buttNuevo = new Button("Nuevo");
		
		
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setWidth("100%");
		//horizontalLayout.setSizeFull();
		
		horizontalLayout.addComponent(buttBuscar);
		horizontalLayout.addComponent(buttLimpiar);
		horizontalLayout.addComponent(buttNuevo);
		
		//Ni idea porque esto, pero va jeje
		horizontalLayout.setComponentAlignment(buttBuscar,Alignment.TOP_LEFT);
		horizontalLayout.setComponentAlignment(buttLimpiar,Alignment.TOP_LEFT);
		horizontalLayout.setComponentAlignment(buttNuevo,Alignment.TOP_LEFT);
		
		horizontalLayout.setExpandRatio(buttBuscar, 1);
		horizontalLayout.setExpandRatio(buttLimpiar, 1);
		horizontalLayout.setExpandRatio(buttNuevo, 1);
		
		//layout.addComponent(horizontalLayout, "left:30%;right:10%;"+"top:20%;bottom:20%;");
		//mainLayout.addComponent(layout);
		gridLayout.addComponent(horizontalLayout, 1, 1);
		mainLayout.addComponent(gridLayout);
		
		
		
		
		
		//-------------------------------------------------------
		label = new Label("Productos encontrados");
		label.addStyleName("title");
		mainLayout.addComponent(label);
		
		table = new Table();
		table.addStyleName("components-inside");
		table.setWidth("700px");
		
		table.setColumnCollapsingAllowed(true);
		// Allow selecting items from the table.
		table.setSelectable(true);
		// Shows feedback from selection.
		final Label current = new Label("Selected: -");
		mainLayout.addComponent(current);
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
		cargarTablaResultados();
		
		table.setPageLength(10);
		mainLayout.addComponent(table);
	}
	
	
	public void cargarTablaResultados() {
		this.cargarTablaResultados(null);
	}
	
	public void cargarTablaResultados(ProductoFilter filter) {
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
					Integer itemIdInteger = (Integer)event.getButton().getData();
					getWindow().showNotification("Link "+itemIdInteger.intValue());
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
	
	
	
	public void agregarListenersABotones() {
		buttBuscar.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				ProductoFilter filter = new ProductoFilter();
				
				if(fieldCodigo!=null)
					filter.setCodigo(fieldCodigo.toString());
				else
					filter.setCodigo(null);
				
				//if(selectTipoDeProducto.isValid())
				for(TipoProducto tp: tipoDeProductos)
					if(tp.getDescripcion().equalsIgnoreCase((String) selectTipoDeProducto.getValue()))
						filter.setIdTipoProducto(tp.getId());
				
				limpiarTabla();
				cargarTablaResultados(filter);
			}
		});
		
		
		buttLimpiar.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				limpiarTabla();
				cargarTablaResultados();
			}
		});
		
		
		buttNuevo.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				
				PopupView popup = new PopupView(null, new TextField("Holaa"));
				
				
				Form form = new Form();
				form.setCaption("Form Caption");
				form.setDescription("This is a description of the Form");
				// Add a field directly to the layout. This field will
				// not be bound to the data source Item of the form.
				form.getLayout().addComponent(new TextField("A Field"));
				// Add a field and bind it to an named item property.
				form.addField("another", new TextField("Another Field"));
				form.setComponentError(new UserError("This is the error ind…"));
				
				form.setFooter(new VerticalLayout());
				form.getFooter().addComponent(new Label("This is the footer area of the Form…"));
				// Have a button bar in the footer.
				HorizontalLayout okbar = new HorizontalLayout();
				okbar.setHeight("25px");
				form.getFooter().addComponent(okbar);
				// Add an Ok (commit), Reset (discard), and Cancel buttons
				// for the form.
				Button okbutton = new Button("OK", form, "commit");
				okbar.addComponent(okbutton);
				okbar.setComponentAlignment(okbutton, Alignment.TOP_RIGHT);
				okbar.addComponent(new Button("Reset", form, "discard"));
				okbar.addComponent(new Button("Cancel"));
				
				mainLayout.addComponent(form);
				
				nuevoProductoSubWindow();
			}
		});
	}
	
	public void nuevoProductoSubWindow() {
		// Create a sub-window and set the content
        Window subWindow = new Window("Sub-window");
        VerticalLayout subContent = new VerticalLayout();
        subContent.setMargin(true);
        subWindow.setContent(subContent);

        // Put some components in it
        subContent.addComponent(new Label("Meatball sub"));
        subContent.addComponent(new Button("Awlright"));

        // Center it in the browser window
        subWindow.center();

        // Open it in the UI
        
        System.out.println("Hola");
        setCompositionRoot(subContent);
        System.out.println("Chau");
	}
	
	
	
	//Getters and Setters para los beans
	public PracticeService getPracticeService() {
		return practiceService;
	}

	public void setPracticeService(PracticeService practiceService) {
		this.practiceService = practiceService;
	}
}