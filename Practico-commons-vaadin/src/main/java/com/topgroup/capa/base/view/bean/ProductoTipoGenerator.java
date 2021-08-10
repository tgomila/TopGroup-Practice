package com.topgroup.capa.base.view.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import com.topgroup.capa.base.domain.model.TipoProducto;
import com.topgroup.commons.vaadin.view.form.FieldGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;

@Component
@Scope("prototype")
public class ProductoTipoGenerator implements FieldGenerator {
	
	@Autowired
	ProductoContainer productoContainer;

	@Override
	public Field createField(Item arg0, Object arg1, com.vaadin.ui.Component arg2) {
		BeanItemContainer<TipoProducto> beanTipos = new BeanItemContainer<TipoProducto>(TipoProducto.class);
		beanTipos.addAll(productoContainer.getAllTipoProductos());
		
		ComboBox comboBox = new ComboBox();	
		comboBox.setCaption("Tipo de producto");	
		comboBox.setContainerDataSource(beanTipos);
		comboBox.setItemCaptionPropertyId("descripcion");
		return comboBox;
	}
	
	
}
