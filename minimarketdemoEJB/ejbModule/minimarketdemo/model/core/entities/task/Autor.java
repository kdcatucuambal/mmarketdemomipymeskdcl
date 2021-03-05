package minimarketdemo.model.core.entities.task;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catucuambak_autor database table.
 * 
 */
@Entity
@Table(name="catucuambak_autor")
@NamedQuery(name="Autor.findAll", query="SELECT a FROM Autor a")
public class Autor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_autor", unique=true, nullable=false)
	private Integer idAutor;

	@Column(name="foto_autor", nullable=false, length=2147483647)
	private String fotoAutor;

	@Column(name="nombre_autor", nullable=false, length=50)
	private String nombreAutor;

	@Column(name="pais_autor", nullable=false, length=50)
	private String paisAutor;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="catucuambakAutor", fetch=FetchType.EAGER)
	private List<Album> catucuambakAlbums;

	public Autor() {
	}

	public Integer getIdAutor() {
		return this.idAutor;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	public String getFotoAutor() {
		return this.fotoAutor;
	}

	public void setFotoAutor(String fotoAutor) {
		this.fotoAutor = fotoAutor;
	}

	public String getNombreAutor() {
		return this.nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getPaisAutor() {
		return this.paisAutor;
	}

	public void setPaisAutor(String paisAutor) {
		this.paisAutor = paisAutor;
	}

	public List<Album> getCatucuambakAlbums() {
		return this.catucuambakAlbums;
	}

	public void setCatucuambakAlbums(List<Album> catucuambakAlbums) {
		this.catucuambakAlbums = catucuambakAlbums;
	}

	public Album addCatucuambakAlbum(Album catucuambakAlbum) {
		getCatucuambakAlbums().add(catucuambakAlbum);
		catucuambakAlbum.setCatucuambakAutor(this);

		return catucuambakAlbum;
	}

	public Album removeCatucuambakAlbum(Album catucuambakAlbum) {
		getCatucuambakAlbums().remove(catucuambakAlbum);
		catucuambakAlbum.setCatucuambakAutor(null);

		return catucuambakAlbum;
	}

}