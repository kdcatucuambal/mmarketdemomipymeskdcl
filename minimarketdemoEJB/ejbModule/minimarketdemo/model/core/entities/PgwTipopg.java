package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pgw_tipopg database table.
 * 
 */
@Entity
@Table(name="pgw_tipopg")
@NamedQuery(name="PgwTipopg.findAll", query="SELECT p FROM PgwTipopg p")
public class PgwTipopg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo", unique=true, nullable=false)
	private Integer idTipo;

	@Column(name="descripcion_tipo", nullable=false, length=50)
	private String descripcionTipo;

	//bi-directional many-to-one association to Pgw
	@OneToMany(mappedBy="pgwTipopg")
	private List<Pgw> pgws;

	public PgwTipopg() {
	}

	public Integer getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcionTipo() {
		return this.descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public List<Pgw> getPgws() {
		return this.pgws;
	}

	public void setPgws(List<Pgw> pgws) {
		this.pgws = pgws;
	}

	public Pgw addPgw(Pgw pgw) {
		getPgws().add(pgw);
		pgw.setPgwTipopg(this);

		return pgw;
	}

	public Pgw removePgw(Pgw pgw) {
		getPgws().remove(pgw);
		pgw.setPgwTipopg(null);

		return pgw;
	}

}