package minimarketdemo.model.core.entities.task;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the catucuambak_cancion database table.
 * 
 */
@Entity
@Table(name="catucuambak_cancion")
@NamedQuery(name="Cancion.findAll", query="SELECT c FROM Cancion c")
public class Cancion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cancion", unique=true, nullable=false)
	private Integer idCancion;

	@Column(name="duracion_cancion", nullable=false, length=5)
	private String duracionCancion;

	@Column(name="link_cancion", nullable=false, length=2147483647)
	private String linkCancion;

	@Column(name="titulo_cancion", nullable=false, length=50)
	private String tituloCancion;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="id_album", nullable=false)
	private Album catucuambakAlbum;

	public Cancion() {
	}

	public Integer getIdCancion() {
		return this.idCancion;
	}

	public void setIdCancion(Integer idCancion) {
		this.idCancion = idCancion;
	}

	public String getDuracionCancion() {
		return this.duracionCancion;
	}

	public void setDuracionCancion(String duracionCancion) {
		this.duracionCancion = duracionCancion;
	}

	public String getLinkCancion() {
		return this.linkCancion;
	}

	public void setLinkCancion(String linkCancion) {
		this.linkCancion = linkCancion;
	}

	public String getTituloCancion() {
		return this.tituloCancion;
	}

	public void setTituloCancion(String tituloCancion) {
		this.tituloCancion = tituloCancion;
	}

	public Album getCatucuambakAlbum() {
		return this.catucuambakAlbum;
	}

	public void setCatucuambakAlbum(Album catucuambakAlbum) {
		this.catucuambakAlbum = catucuambakAlbum;
	}

}