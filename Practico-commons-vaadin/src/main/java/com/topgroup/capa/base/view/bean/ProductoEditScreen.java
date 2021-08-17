package com.topgroup.capa.base.view.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.commons.vaadin.callback.CallbackForm;
import com.topgroup.commons.vaadin.field.DataCombobox;
import com.topgroup.commons.vaadin.util.VaadinUtil;
import com.topgroup.commons.vaadin.view.form.PanelBeanForm;
import com.topgroup.commons.vaadin.view.panel.BaseFormPanel;
import com.vaadin.ui.TextField;
@Component()
@Scope("prototype")
public class ProductoEditScreen extends BaseFormPanel<ProductoViewBean> implements CallbackForm {
	
	private static final long serialVersionUID = 1L;
	private static final String[] VISIBLE_PROPERTIES = new String[] {VaadinUtil.getMessage("ProductoViewBean.codigo"),
			VaadinUtil.getMessage("ProductoViewBean.descripcion"), VaadinUtil.getMessage("ProductoViewBean.tipoProducto"),
			VaadinUtil.getMessage("ProductoViewBean.productosPorPaquete")};
	
	@Autowired
	private ProductoTipoGenerator productoTipoGenerator;
	
	@Autowired
	private ProductoContainer productoContainer;
	
	private ProductoSearchPanel productoSearchPanel;
	
	private TextField codigo = null;
	private TextField descripcion = null;
	private DataCombobox<TipoProducto> tipoProductoCombobox = null;
	private TextField productosPorPaquete = null;
	
	@Override
	protected void afterInitForm() {
		System.out.println(" - Leo ProductoEditScreen.afterInitForm");
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
		if(tipoProductoCombobox != null) {
			tipoProductoCombobox.setRequired(true);
			tipoProductoCombobox.setRequiredError("Ingrese un tipo de producto");
		}
		productosPorPaquete = (TextField) form.getField("productosPorPaquete");
		productosPorPaquete.setRequired(true);
		productosPorPaquete.setMaxLength(2);
		productosPorPaquete.setRequiredError(VaadinUtil.getMessage("errors.required", productosPorPaquete.getCaption()));
		
		
		//tipoProducto = (TextField) form.getField("tipoProducto");
		//tipoProducto.setRequired(true);
		//tipoProducto.setMaxLength(13);
		//tipoProducto.setRequiredError(VaadinUtil.getMessage("errors.required", tipoProducto.getCaption()));
	}
	
	@Override
	public void accept(Event arg0) {
		ProductoViewBean bean = form.getBeanItem().getBean();
		System.out.println("Nuevo producto:");
		if(bean.getCodigo()!=null)
			System.out.println("  ProductoBean: codigo: " + bean.getCodigo());
		if(bean.getDescripcion()!=null)
			System.out.println("  Descripcion: " + bean.getDescripcion());
		if(bean.getFechaAlta()!=null)
			System.out.println("  FechaAlta: "+bean.getFechaAlta());
		if(bean.getTipoProducto()!=null)
			System.out.println("  TipoProducto:"+bean.getTipoProducto().getDescripcion());
		
		
		productoContainer.guardar(bean);
		closeVindow();
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
		System.out.println("Cancelado");
		closeVindow();
	}

	@Override
	protected String getTitlePanel() {
		return VaadinUtil.getMessage("ProductoEditScreen");
	}

	@Override
	protected String[] getVisibleItemProperties() {
		return VISIBLE_PROPERTIES;
	}
	
	protected void addFieldGenerator(PanelBeanForm<ProductoViewBean> beanForm) {
		super.addFieldGenerator(beanForm);
		beanForm.addFieldGenerator(VISIBLE_PROPERTIES[2], productoTipoGenerator);
	}
	
}
