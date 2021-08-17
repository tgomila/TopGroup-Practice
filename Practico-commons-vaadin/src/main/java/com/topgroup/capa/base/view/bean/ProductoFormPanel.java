package com.topgroup.capa.base.view.bean;

import java.util.Date;

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
	private static final String[] VISIBLE_ITEMS = new String[] {VaadinUtil.getMessage("ProductoViewBean.codigo"),
			VaadinUtil.getMessage("ProductoViewBean.descripcion"), VaadinUtil.getMessage("ProductoViewBean.tipoProducto"),
			VaadinUtil.getMessage("ProductoViewBean.productosPorPaquete")};
	
	@Autowired
	ProductoFilter productoFilter;
	
	@Autowired
	ProductoContainer productoContainer;
	
	@Autowired
	ProductoTipoGenerator generator;
	
	
	@Override
	protected void addFieldGenerator(PanelBeanForm<ProductoFilter> beanForm) {
		beanForm.addFieldGenerator(VISIBLE_ITEMS[2], generator);
	}
	
	@Override
	protected String getTitlePanel() {
		return VaadinUtil.getMessage("ProductoFormPanel");
	}

	@Override
	protected String[] getVisibleItemProperties() {
		return VISIBLE_ITEMS;
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
		System.out.println("Nuevo producto:");
		if(productoFilter.getCodigo()!=null)
			System.out.println("  ProductoBean: codigo: " + productoFilter.getCodigo());
		if(productoFilter.getDescripcion()!=null)
			System.out.println("  Descripcion: " + productoFilter.getDescripcion());
		if(productoFilter.getFechaAlta()!=null)
			System.out.println("  FechaAlta: "+productoFilter.getFechaAlta());
		if(productoFilter.getTipoProducto()!=null)
			System.out.println("  TipoProducto:"+productoFilter.getTipoProducto().getDescripcion());
		ProductoViewBean productoNuevo = new ProductoViewBean(productoFilter);
		productoNuevo.setFechaAlta(new Date());
		productoContainer.guardar(productoNuevo);
		closeVindow();
	}

	@Override
	public void cancel(Event arg0) {
		// TODO Auto-generated method stub
		closeVindow();
	}
	
}
