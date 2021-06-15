package com.tg.practice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	//Las propiedades de Medida en la BD estan dentro de Producto
	@Embedded
	private Medida medida;
	private String codigo;
	private String descripcion;
	private Short productosPorPaquete;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Familia familia;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private TipoProducto tipoProducto;
	private Boolean inferior;
	
	@ManyToMany
	private List<Maquina> maquinas;
	
	@OneToOne
	private Stock stock;
	
	//Constructores
	public Producto() {
		super();
		//medida = new Medida();
		maquinas = new ArrayList<Maquina>();
	}
	public Producto(Long id, Medida medida, String codigo, String descripcion, Short productosPorPaquete,
			Familia familia, TipoProducto tipoProducto, Boolean inferior, List<Maquina> maquinas, Stock stock) {
		super();
		this.id = id;
		this.medida = medida;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.productosPorPaquete = productosPorPaquete;
		this.familia = familia;
		this.tipoProducto = tipoProducto;
		this.inferior = inferior;
		this.maquinas = maquinas;
		this.stock = stock;
	}
	
	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Medida getMedida() {
		return medida;
	}
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Short getProductosPorPaquete() {
		return productosPorPaquete;
	}
	public void setProductosPorPaquete(Short productosPorPaquete) {
		this.productosPorPaquete = productosPorPaquete;
	}
	
	public Familia getFamilia() {
		return familia;
	}
	
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
	
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}
	
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
	public Boolean getInferior() {
		return inferior;
	}
	
	public void setInferior(Boolean inferior) {
		this.inferior = inferior;
	}
	
	public List<Maquina> getMaquinas() {
		return maquinas;
	}
	
	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
}
