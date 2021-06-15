package com.tg.practice.service;

import com.tg.practice.DAOInterface.*;

public class ServiceLocator {
	
	private static ServiceLocator soleInstance;
	
	private EmpleadoDAO empleadoDAO;
	private FamiliaDAO familiaDAO;
	private MaquinaDAO maquinaDAO;
	private PedidoDAO pedidoDAO;
	private ProductoDAO productoDAO;
	private StockDAO stockDAO;
	private TipoProductoDAO tipoProductoDAO;
	
	public static StockDAO stockDAO () {
		return soleInstance.stockDAO;
	}

	public static ServiceLocator getSoleInstance() {
		return soleInstance;
	}

	public static void setSoleInstance(ServiceLocator soleInstance) {
		ServiceLocator.soleInstance = soleInstance;
	}

	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}

	public FamiliaDAO getFamiliaDAO() {
		return familiaDAO;
	}

	public void setFamiliaDAO(FamiliaDAO familiaDAO) {
		this.familiaDAO = familiaDAO;
	}

	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public MaquinaDAO getMaquinaDAO() {
		return maquinaDAO;
	}

	public void setMaquinaDAO(MaquinaDAO maquinaDAO) {
		this.maquinaDAO = maquinaDAO;
	}

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public StockDAO getStockDAO() {
		return stockDAO;
	}

	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	public TipoProductoDAO getTipoProductoDAO() {
		return tipoProductoDAO;
	}

	public void setTipoProductoDAO(TipoProductoDAO tipoProductoDAO) {
		this.tipoProductoDAO = tipoProductoDAO;
	}
	
	
	
	
}
