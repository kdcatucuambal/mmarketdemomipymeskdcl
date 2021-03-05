package minimarketdemo.model.core.entities.exam;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inv_movimiento database table.
 * 
 */
@Entity
@Table(name="inv_movimiento")
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_movimiento", unique=true, nullable=false)
	private Integer idMovimiento;

	@Column(nullable=false)
	private Integer cantidad;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto invProducto;

	public Movimiento() {
	}

	public Integer getIdMovimiento() {
		return this.idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(Producto invProducto) {
		this.invProducto = invProducto;
	}

}