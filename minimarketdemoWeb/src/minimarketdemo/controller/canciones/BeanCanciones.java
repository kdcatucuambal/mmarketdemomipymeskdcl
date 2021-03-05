package minimarketdemo.controller.canciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.canciones.managers.ManagerAlbums;
import minimarketdemo.model.canciones.managers.ManagerCanciones;
import minimarketdemo.model.core.entities.task.Album;
import minimarketdemo.model.core.entities.task.Cancion;

@Named
@SessionScoped
public class BeanCanciones implements Serializable {

	@EJB
	private ManagerCanciones mCanciones;

	@EJB
	private ManagerAlbums mAlbums;

	private List<Cancion> canciones;

	private Cancion cancion;

	private Album album;

	private String txtLike;

	private String valueButton;

	public BeanCanciones() {

		// this.cancionHandler = this.mCanciones.findById(1);
	}

	@PostConstruct
	public void inicializar() {
		this.cancion = this.mCanciones.findById(1);
	}

	public String actionLoadCanciones() {
		this.canciones = mCanciones.findAll();
		return "canciones" + "?faces-redirect=true";
	}

	public void actionDeleteCancion() {
		try {
			this.mCanciones.delete(this.cancion.getIdCancion());
			JSFUtil.crearMensajeWARN("Canción eliminada: " + this.cancion.getTituloCancion());
			this.canciones = null;
			this.canciones = mCanciones.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeERROR("No se pudo eliminar la canción: " + e.getMessage());
		}

		this.cancion = null;

	}

	public void actionSetCancion(Cancion c) {
		this.cancion = c;
		this.txtLike = c.getCatucuambakAlbum().getNombreAlbum();
		this.valueButton = "Editar canción";

	}

	public void openNew() {
		this.cancion = new Cancion();
		this.album = null;
		this.txtLike = "";
		this.valueButton = "Crear canción";
	}

	public void saveCancion() {
		System.out.println("ejecuando save");
		List<Album> f = this.mAlbums.findByWhere("nombreAlbum", this.txtLike);

		if (f.size() > 0) {
			this.cancion.setCatucuambakAlbum(f.get(0));
			if (this.cancion.getIdCancion() == null) {
				this.mCanciones.create(this.cancion);
				JSFUtil.crearMensajeINFO("Canción nueva: " + this.cancion.getTituloCancion());
			} else {
				this.mCanciones.update(this.cancion);
				JSFUtil.crearMensajeWARN("Canción editada: " + this.cancion.getIdCancion());

			}
			PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
			this.canciones = mCanciones.findAll();
		}

		this.cancion = null;
	}

	public List<String> completeText(String query) {
		String queryLowerCase = query.toLowerCase();
		List<String> albumList = new ArrayList<>();
		List<Album> albums = this.mAlbums.findAll();
		for (Album album : albums) {
			albumList.add(album.getNombreAlbum());
		}

		return albumList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
	}

	public List<Album> completeAlbum(String query) {
		String queryLowerCase = query.toLowerCase();
		List<Album> albums = this.mAlbums.findAll();
		return albums.stream().filter(t -> t.getNombreAlbum().toLowerCase().contains(queryLowerCase))
				.collect(Collectors.toList());
	}

	public void onItemSelect(SelectEvent<String> event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Country Selected", event.getObject()));
	}

	public void onEmptyMessageSelect() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empty message selected"));
	}

	public Cancion getCancion() {
		return cancion;
	}

	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	public String getTxtLike() {
		return txtLike;
	}

	public void setTxtLike(String txtLike) {
		this.txtLike = txtLike;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getValueButton() {
		return valueButton;
	}

	public void setValueButton(String valueButton) {
		this.valueButton = valueButton;
	}

	private static final long serialVersionUID = 1L;

}
