package com.topgroup.capa.base.view.bean;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.persistence.filter.ProductoFilter;
import com.topgroup.commons.vaadin.callback.CallbackForm;
import com.topgroup.commons.vaadin.util.VaadinUtil;
import com.topgroup.commons.vaadin.view.form.PanelBeanForm;
import com.topgroup.commons.vaadin.view.panel.BaseFormPanel;
import com.vaadin.ui.Window;

@Component()
@Scope("prototype")
public class ProductoFormPanel extends BaseFormPanel<ProductoFilter> implements CallbackForm{
	//ProductoEditScreen.java creo que reemplaza a este Form
	
	private static final long serialVersionUID = 1L;
	private static final String[] VISIBLE_COLUMNS = new String[] {"codigo", "descripcion", "tipoProducto"};
	
	@Autowired
	ProductoFilter productoFilter;
	
	@Autowired
	ProductoContainer productoContainer;
	
	@Autowired
	ProductoTipoGenerator generator;
	
	@Override
	protected void addFieldGenerator(PanelBeanForm<ProductoFilter> beanForm) {
		beanForm.addFieldGenerator(VISIBLE_COLUMNS[2], generator);
	}
	
	@Override
	protected String getTitlePanel() {
		return VaadinUtil.getMessage("Edición de productos");
	}

	@Override
	protected String[] getVisibleItemProperties() {
		return VISIBLE_COLUMNS;
	}
	
	
	
	//T parameter es bean
	/*public void init(T parameter, final CallbackForm callback) {
	}
	public Window createWindow() {
		return null;
	}
	public void setModeForm(int modeForm) {
	}*/

	@Override
	public void accept(Event arg0) {
		// TODO Auto-generated method stub
		ProductoViewBean productoNuevo = new ProductoViewBean(productoFilter);
		productoContainer.guardar(productoNuevo);
		
	}

	@Override
	public void cancel(Event arg0) {
		// TODO Auto-generated method stub
		closeVindow();
	}
	
}
