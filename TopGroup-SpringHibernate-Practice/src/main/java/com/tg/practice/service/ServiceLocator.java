package com.tg.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.practice.DAOInterface.*;

@Service
public class ServiceLocator {
	
	private static ServiceLocator soleInstance;
	
	@Autowired
	private EmpleadoDAO empleadoDAO;
	@Autowired
	private FamiliaDAO familiaDAO;
	@Autowired
	private MaquinaDAO maquinaDAO;
	@Autowired
	private PedidoDAO pedidoDAO;
	@Autowired
	private ProductoDAO productoDAO;
	@Autowired
	private StockDAO stockDAO;
	@Autowired
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
	
	@Autowired
	public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}

	public FamiliaDAO getFamiliaDAO() {
		return familiaDAO;
	}
	
	@Autowired
	public void setFamiliaDAO(FamiliaDAO familiaDAO) {
		this.familiaDAO = familiaDAO;
	}

	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}
	
	@Autowired
	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}
	
	public MaquinaDAO getMaquinaDAO() {
		return maquinaDAO;
	}
	
	@Autowired
	public void setMaquinaDAO(MaquinaDAO maquinaDAO) {
		this.maquinaDAO = maquinaDAO;
	}
	
	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}
	
	@Autowired
	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}
	
	public StockDAO getStockDAO() {
		return stockDAO;
	}
	
	@Autowired
	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}
	
	public TipoProductoDAO getTipoProductoDAO() {
		return tipoProductoDAO;
	}
	
	@Autowired
	public void setTipoProductoDAO(TipoProductoDAO tipoProductoDAO) {
		this.tipoProductoDAO = tipoProductoDAO;
	}
	
	
	
	
}
