package com.topgroup.capa.base.view.bean;

import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

public class TipoProductoColumnGenerator implements ColumnGenerator {

	private static final long serialVersionUID = 1L;

	@Override
	public Object generateCell(Table source, Object itemId, Object columnId) {
		ProductoViewBean row = (ProductoViewBean) itemId;
		Label tipoProductoLabel = new Label(row.getTipoProducto());
		
		//Borrar esta linea luego de probar
		tipoProductoLabel.addStyleName("TestStyleName");
		return tipoProductoLabel;
	}

}
