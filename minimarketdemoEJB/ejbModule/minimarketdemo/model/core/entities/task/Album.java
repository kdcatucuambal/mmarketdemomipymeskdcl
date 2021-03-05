package minimarketdemo.model.core.entities.task;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catucuambak_album database table.
 * 
 */
@Entity
@Table(name="catucuambak_album")
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_album", unique=true, nullable=false)
	private Integer idAlbum;

	@Column(name="anio_album", nullable=false)
	private Integer anioAlbum;

	@Column(name="foto_album", nullable=false, length=2147483647)
	private String fotoAlbum;

	@Column(name="genero_album", nullable=false, length=50)
	private String generoAlbum;

	@Column(name="nombre_album", nullable=false, length=50)
	private String nombreAlbum;

	//bi-directional many-to-one association to Autor
	@ManyToOne
	@JoinColumn(name="id_autor", nullable=false)
	private Autor catucuambakAutor;

	//bi-directional many-to-one association to Cancion
	@OneToMany(mappedBy="catucuambakAlbum", fetch=FetchType.EAGER)
	private List<Cancion> catucuambakCancions;

	public Album() {
	}

	public Integer getIdAlbum() {
		return this.idAlbum;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

	public Integer getAnioAlbum() {
		return this.anioAlbum;
	}

	public void setAnioAlbum(Integer anioAlbum) {
		this.anioAlbum = anioAlbum;
	}

	public String getFotoAlbum() {
		return this.fotoAlbum;
	}

	public void setFotoAlbum(String fotoAlbum) {
		this.fotoAlbum = fotoAlbum;
	}

	public String getGeneroAlbum() {
		return this.generoAlbum;
	}

	public void setGeneroAlbum(String generoAlbum) {
		this.generoAlbum = generoAlbum;
	}

	public String getNombreAlbum() {
		return this.nombreAlbum;
	}

	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}

	public Autor getCatucuambakAutor() {
		return this.catucuambakAutor;
	}

	public void setCatucuambakAutor(Autor catucuambakAutor) {
		this.catucuambakAutor = catucuambakAutor;
	}

	public List<Cancion> getCatucuambakCancions() {
		return this.catucuambakCancions;
	}

	public void setCatucuambakCancions(List<Cancion> catucuambakCancions) {
		this.catucuambakCancions = catucuambakCancions;
	}

	public Cancion addCatucuambakCancion(Cancion catucuambakCancion) {
		getCatucuambakCancions().add(catucuambakCancion);
		catucuambakCancion.setCatucuambakAlbum(this);

		return catucuambakCancion;
	}

	public Cancion removeCatucuambakCancion(Cancion catucuambakCancion) {
		getCatucuambakCancions().remove(catucuambakCancion);
		catucuambakCancion.setCatucuambakAlbum(null);

		return catucuambakCancion;
	}

}