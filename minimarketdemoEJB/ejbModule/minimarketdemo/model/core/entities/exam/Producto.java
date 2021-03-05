package minimarketdemo.model.core.entities.exam;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the inv_producto database table.
 * 
 */
@Entity
@Table(name="inv_producto")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto", unique=true, nullable=false)
	private Integer idProducto;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal precio;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="invProducto", fetch=FetchType.EAGER)
	private List<Movimiento> invMovimientos;

	public Producto() {
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public List<Movimiento> getInvMovimientos() {
		return this.invMovimientos;
	}

	public void setInvMovimientos(List<Movimiento> invMovimientos) {
		this.invMovimientos = invMovimientos;
	}

	public Movimiento addInvMovimiento(Movimiento invMovimiento) {
		getInvMovimientos().add(invMovimiento);
		invMovimiento.setInvProducto(this);

		return invMovimiento;
	}

	public Movimiento removeInvMovimiento(Movimiento invMovimiento) {
		getInvMovimientos().remove(invMovimiento);
		invMovimiento.setInvProducto(null);

		return invMovimiento;
	}

}