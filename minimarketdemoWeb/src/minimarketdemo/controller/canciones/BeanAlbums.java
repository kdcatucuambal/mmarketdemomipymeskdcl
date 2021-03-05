package minimarketdemo.controller.canciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.canciones.managers.ManagerAlbums;
import minimarketdemo.model.canciones.managers.ManagerAutores;
import minimarketdemo.model.core.entities.task.*;

@Named
@SessionScoped
public class BeanAlbums implements Serializable {

	@EJB
	private ManagerAutores mAutores;
	@EJB
	private ManagerAlbums mAlbums;

	private List<Album> albums;

	private List<Cancion> cancionesbyAlbum;

	private Album album;

	private Autor autor;

	private String txtLike;

	private String valueButton;

	public BeanAlbums() {
		// TODO Auto-generated constructor stub
	}

	public String actionLoadAlbums() {
		this.albums = mAlbums.findAll();
		return "albums" + "?faces-redirect=true";
	}

	public void actionDeleteAlbum() {
		try {
			this.mAlbums.delete(this.album.getIdAlbum());
			JSFUtil.crearMensajeWARN("Canción eliminada: " + this.album.getNombreAlbum());
			this.albums = null;
			this.albums = mAlbums.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeERROR("No se pudo eliminar el álbum, contiene canciones!");
		}

		this.album = null;

	}

	public void actionSetAlbum(Album a) {
		this.album = a;
		this.txtLike = a.getCatucuambakAutor().getNombreAutor();
		this.valueButton = "Editar Album";
		System.out.println("Total canciones: " + this.album.getCatucuambakCancions().size());
		// this.cancionesbyAlbum =
		// this.mCanciones.findByWhere("catucuambakAlbum.idAlbum",
		// a.getIdAlbum().toString());
		// System.out.println("Canciones: " + this.cancionesbyAlbum.size());
	}

	public void openNew() {
		this.album = new Album();
		this.autor = null;
		this.txtLike = "";
		this.valueButton = "Crear Album";
	}

	public void saveAlbum() {

		List<Autor> f = this.mAutores.findByWhere("nombreAutor", this.txtLike);

		if (f.size() > 0) {
			this.album.setCatucuambakAutor(f.get(0));
			if (this.album.getIdAlbum() == null) {
				this.mAlbums.create(this.album);
				JSFUtil.crearMensajeINFO("Album nuevo: " + this.album.getIdAlbum());
			} else {
				this.mAlbums.update(this.album);
				JSFUtil.crearMensajeWARN("Album editado: " + this.album.getIdAlbum());

			}
			PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
			this.albums = this.mAlbums.findAll();
		}

		this.album = null;
	}

	public List<String> completeText(String query) {
		String queryLowerCase = query.toLowerCase();
		List<String> autorList = new ArrayList<>();
		List<Autor> autors = this.mAutores.findAll();
		for (Autor autor : autors) {
			autorList.add(autor.getNombreAutor());
		}

		return autorList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
	}

	private static final long serialVersionUID = 1L;

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getTxtLike() {
		return txtLike;
	}

	public void setTxtLike(String txtLike) {
		this.txtLike = txtLike;
	}

	public String getValueButton() {
		return valueButton;
	}

	public void setValueButton(String valueButton) {
		this.valueButton = valueButton;
	}

	public List<Cancion> getCancionesbyAlbum() {
		return cancionesbyAlbum;
	}

	public void setCancionesbyAlbum(List<Cancion> cancionesbyAlbum) {
		this.cancionesbyAlbum = cancionesbyAlbum;
	}

}
