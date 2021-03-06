package com.topgroup.capa.base.view.screen;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class PracticeScreenTest extends CustomComponent {

	private static final long serialVersionUID = -4479639018984308437L;
	
	private VerticalLayout mainLayout;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	public PracticeScreenTest() {
		buildMainLayout();
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
		
		Label label = new Label("Test de componentes");
		label.addStyleName("title");
		mainLayout.addComponent(label);
		
		
		
		
		HorizontalLayout horizontal = new HorizontalLayout();
		horizontal.addComponent(new TextField("Name"));
		horizontal.addComponent(new TextField("Street address"));
		horizontal.addComponent(new TextField("Postal code"));
		mainLayout.addComponent(horizontal);
		
		// A FormLayout used outside the context of a Form
		FormLayout fl = new FormLayout();// Make the FormLayout shrink to its contents
		fl.setSizeUndefined();
		TextField tf = new TextField("A Field");
		fl.addComponent(tf);
		// Mark the first field as required
		tf.setRequired(true);
		tf.setRequiredError("The Field may not be empty.");
		TextField tf2 = new TextField("Another Field");
		fl.addComponent(tf2);
		// Set the second field straing to error state with a message.
		tf2.setComponentError( new UserError("This is the error indicator of a Field."));
		
		mainLayout.addComponent(fl);
		
		
		
		
		

		mainLayout.setHeight(Sizeable.SIZE_UNDEFINED, 0);
		
		GridLayout grid = new GridLayout(4, 4);
		grid.setWidth("100%");	//SIN ESTO el expanded ratio NO FUNCIONA, el de column
		grid.setHeight(Sizeable.SIZE_UNDEFINED, 0);
		grid.addStyleName("Head");
		// Fill out the first row using the cursor.
		grid.addComponent(new Button("R/C 1"));
		for (int i = 0; i < 3; i++)
		{
			grid.addComponent(new Button("Col " + (grid.getCursorX() + 1)));
		}
		// Fill out the first column using coordinates.
		for (int i = 1; i < 4; i++)
		{
			grid.addComponent(new Button("Row " + i), 0, i);
		}
		// Add some components of various shapes.
		grid.addComponent(new Button("3x1 button"), 1, 1, 3, 1);
		grid.addComponent(new Label("1x2 cell"), 1, 2, 1, 3);
		grid.setColumnExpandRatio(0, 5);
		grid.setColumnExpandRatio(1, 5);
		grid.setColumnExpandRatio(2, 15);
		grid.setRowExpandRatio(0, 15);
		grid.setRowExpandRatio(1, 5);
		grid.setRowExpandRatio(2, 5);
		mainLayout.addComponent(grid);
		
		
		/*Panel panel = new Panel("A Panel");
		panel.setSizeFull(); // Fill the specified area
		AbsoluteLayout layout = new AbsoluteLayout();
		layout.setWidth("400px");
		layout.setHeight("250px");
		
		Button buttRight = new Button( "right: 0px; bottom: 100px;");
		panel.addComponent(buttRight);//, "right: 0px; bottom: 100px;");
		panel.setVisible(true);
		
		layout.addComponent(panel, "left:30%;right:10%;"+"top:20%;bottom:20%;");
		mainLayout.addComponent(layout);*/
		
		//HorizontalLayout horizontalLayout = new HorizontalLayout();
		//horizontalLayout.setWidth("400px");
		//mainLayout.addComponent(horizontalLayout);
		
		
		
		// Create a table and add a style to
		// allow setting the row height in theme.
		Table table = new Table();
		table.addStyleName("components-inside");

		/* Define the names and data types of columns.
		 * The "default value" parameter is meaningless here. */
		table.addContainerProperty("Sum",            Label.class,     null);
		table.addContainerProperty("Is Transferred", CheckBox.class,  null);
		table.addContainerProperty("Comments",       TextField.class, null);
		table.addContainerProperty("Details",        Button.class,    null);

		/* Add a few items in the table. */
		for (int i=0; i<100; i++) {
		    // Create the fields for the current table row
		    Label sumField = new Label(String.format(
		                   "Sum is <b>$%04.2f</b><br/><i>(VAT incl.)</i>",
		                   new Object[] {new Double(Math.random()*1000)}) );
		    CheckBox transferredField = new CheckBox("is transferred");

		    // Multiline text field. This required modifying the
		    // height of the table row.
		    TextField commentsField = new TextField();
		    commentsField.setRows(3);

		    // The Table item identifier for the row.
		    Integer itemId = new Integer(i);

		    // Create a button and handle its click. A Button does not
		    // know the item it is contained in, so we have to store the
		    // item ID as user-defined data.
		    Button detailsField = new Button("show details");
		    detailsField.setData(itemId);
		    detailsField.addListener(new Button.ClickListener() {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
		            // Get the item identifier from the user-defined data.
		            Integer iid = (Integer)event.getButton().getData();
		            //Notification.show("Link " +
		            //                  iid.intValue() + " clicked.");
		        }
		    });
		    detailsField.addStyleName("link");

		    // Create the table row.
		    table.addItem(new Object[] {sumField, transferredField,
		                                commentsField, detailsField},
		                  itemId);
		}

		// Show just three rows because they are so high.
		table.setPageLength(3);
		mainLayout.addComponent(table);
	}


}
