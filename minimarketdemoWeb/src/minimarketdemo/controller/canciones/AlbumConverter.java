package minimarketdemo.controller.canciones;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import minimarketdemo.model.canciones.managers.ManagerAlbums;
import minimarketdemo.model.core.entities.task.Album;

@Named
@FacesConverter(value = "albumConverter", managed = true)
public class AlbumConverter implements Converter<Album> {

	@EJB
	private ManagerAlbums managerAlbums;

	public AlbumConverter() {
		System.out.println("Iniciar Converter");
	}

	@Override
	public Album getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("INIT AS OBJECT: " + value);
		if (value != null && value.trim().length() > 0) {
			try {
				return this.managerAlbums.findAll().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Album value) {
		if (value != null) {
			return String.valueOf(value.getIdAlbum());
		} else {
			return null;
		}
	}

}
