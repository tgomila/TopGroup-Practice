package com.topgroup.capa.base.view.bean;

import com.topgroup.commons.vaadin.callback.CallbackForm;
import com.topgroup.commons.vaadin.util.VaadinUtil;
import com.topgroup.commons.vaadin.view.panel.BaseFormPanel;
import com.vaadin.ui.TextField;

public class ProductoEditScreen extends BaseFormPanel<ProductoViewBean> implements CallbackForm {
	
	private static final long serialVersionUID = 1L;
	private final String[] VISIBLE_PROPERTIES = new String[] {"codigo", "descripcion","tipoProducto"};
	
	private ProductoContainer productoContainer = new ProductoContainer();
	private ProductoSearchPanel productoSearchPanel;
	
	private TextField codigo = null;
	private TextField descripcion = null;
	private TextField tipoProducto = null;
	
	@Override
	protected void afterInitForm() {
		super.afterInitForm();
		addValidatorsEdicion();
	}
	
	@SuppressWarnings("deprecation")
	private void addValidatorsEdicion() {
		
		
		codigo = (TextField) form.getField("codigo");
		codigo.setRequired(true);
		codigo.setMaxLength(15);
		codigo.setRequiredError(VaadinUtil.getMessage("errors.required", codigo.getCaption()));
		descripcion = (TextField) form.getField("descripcion");
		descripcion.setRequired(true);
		descripcion.setMaxLength(30);
		descripcion.setRequiredError(VaadinUtil.getMessage("errors.required", descripcion.getCaption()));
		tipoProducto = (TextField) form.getField("tipoProducto");
		tipoProducto.setRequired(true);
		tipoProducto.setMaxLength(13);
		tipoProducto.setRequiredError(VaadinUtil.getMessage("errors.required", tipoProducto.getCaption()));
	}
	
	@Override
	public void accept(Event arg0) {
		ProductoViewBean bean = form.getBeanItem().getBean();
		productoContainer.guardar(bean);
		doSearch();
		
		
	}
	
	private void doSearch() {
		if (productoSearchPanel != null) {
			productoSearchPanel.search(null);
		}
	}
	
	public void setProductoSearchPanel(ProductoSearchPanel productoSearchPanel) {
		this.productoSearchPanel = productoSearchPanel;
	}

	@Override
	public void cancel(Event arg0) {
		// TODO Auto-generated method stub
		System.out.println("Cancelado");
	}

	@Override
	protected String getTitlePanel() {
		return VaadinUtil.getMessage("Detalle/Nuevo/Edición");
	}

	@Override
	protected String[] getVisibleItemProperties() {
		return VISIBLE_PROPERTIES;
	}
	
}
