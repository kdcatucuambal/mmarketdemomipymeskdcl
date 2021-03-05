package minimarketdemo.controller.canciones;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
public class BeanArtistas implements Serializable {

	@EJB
	private ManagerAlbums mAlbums;

	@EJB
	private ManagerAutores mAutores;

	List<Autor> autores;

	private Autor autor;

	private String valueButton;

	public BeanArtistas() {

	}

	@PostConstruct
	public void inicializar() {

	}

	public String actionLoadAutores() {
		this.autores = mAutores.findAll();
		return "artistas" + "?faces-redirect=true";
	}

	public void actionSetAutor(Autor autor) {
		this.autor = autor;
		this.valueButton = "Editar Autor";

	}

	public void openNew() {
		this.autor = new Autor();
		this.valueButton = "Crear Autor";
	}

	public void actionDeleteAutor() {
		try {
			this.mAutores.delete(this.autor.getIdAutor());
			this.autores = null;
			this.autores = mAutores.findAll();
			JSFUtil.crearMensajeWARN("Autor eliminado: " + this.autor.getIdAutor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeERROR("No se pudo eliminar la canción: " + e.getMessage());
		}

		this.autor = null;

	}

	public void saveAutor() {

		if (this.autor.getIdAutor() == null) {
			this.mAutores.create(this.autor);
			JSFUtil.crearMensajeINFO("Autor nuevo: " + this.autor.getIdAutor());
		} else {
			this.mAutores.update(this.autor);
			JSFUtil.crearMensajeWARN("Autor editado: " + this.autor.getIdAutor());

		}
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		this.autores = mAutores.findAll();
		this.autor = null;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getValueButton() {
		return valueButton;
	}

	public void setValueButton(String valueButton) {
		this.valueButton = valueButton;
	}

	private static final long serialVersionUID = 1L;

}
