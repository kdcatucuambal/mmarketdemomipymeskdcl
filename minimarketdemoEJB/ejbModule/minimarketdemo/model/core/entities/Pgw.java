package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pgw database table.
 * 
 */
@Entity
@Table(name="pgw")
@NamedQuery(name="Pgw.findAll", query="SELECT p FROM Pgw p")
public class Pgw implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pgw", unique=true, nullable=false)
	private Integer idPgw;

	@Column(nullable=false, length=100)
	private String direccion;

	@Column(nullable=false, length=50)
	private String titulo;

	//bi-directional many-to-one association to PgwTipopg
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo")
	private PgwTipopg pgwTipopg;

	public Pgw() {
	}

	public Integer getIdPgw() {
		return this.idPgw;
	}

	public void setIdPgw(Integer idPgw) {
		this.idPgw = idPgw;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public PgwTipopg getPgwTipopg() {
		return this.pgwTipopg;
	}

	public void setPgwTipopg(PgwTipopg pgwTipopg) {
		this.pgwTipopg = pgwTipopg;
	}

}