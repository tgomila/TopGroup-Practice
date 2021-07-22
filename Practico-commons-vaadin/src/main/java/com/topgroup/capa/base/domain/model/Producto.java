package com.topgroup.capa.base.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Producto implements Serializable {

	private static final long serialVersionUID = -1353902477625354815L;

	@Id
	@GeneratedValue
	private Long id;

	@Embedded
	private Medida medida;

	private String codigo;

	private String descripcion;

	private Short productosPorPaquete;

	@ManyToOne(fetch = FetchType.LAZY)
	private Familia familia;

	@ManyToOne(fetch = FetchType.LAZY)
	private TipoProducto tipoProducto;

	private Boolean inferior;

	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@ManyToMany
	@JoinTable(name = "PROD_MAQ", joinColumns = { @JoinColumn(name = "idProd") }, inverseJoinColumns = { @JoinColumn(name = "idMaquina") })
	private Collection<Maquina> maquinas;

	@OneToOne(mappedBy = "producto")
	private Stock stock;

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

	public Collection<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(Collection<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", medida=" + medida + ", codigo="
				+ codigo + ", descripcion=" + descripcion
				+ ", productosPorPaquete=" + productosPorPaquete + ", familia="
				+ familia + ", tipoProducto=" + tipoProducto + ", inferior="
				+ inferior + ", maquinas=" + maquinas + ", stock=" + stock
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
