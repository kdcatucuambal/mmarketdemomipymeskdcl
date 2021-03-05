package minimarketdemo.model.canciones.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.task.Album;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerAlbums
 */
@Stateless
@LocalBean
public class ManagerAlbums implements ICrud {
	@EJB
	private ManagerDAO mDAO;

	/**
	 * Default constructor.
	 */
	public ManagerAlbums() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Object newObject) {
		try {
			this.mDAO.insertar((Album) newObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Object idObject) throws Exception {
		try {
			this.mDAO.eliminar(Album.class, idObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("No se pudo eliminar el dato: " + e.getMessage());
		}

	}

	@Override
	public void update(Object upObject) {
		try {
			this.mDAO.actualizar((Album) upObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Album findById(Object idObject) {
		Album album = null;
		try {
			album = (Album) this.mDAO.findById(Album.class, idObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return album;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> findByWhere(String field, String value) {
		String where = "o.".concat(field).concat("=").concat("'").concat(value).concat("'");
		return this.mDAO.findWhere(Album.class, where, "nombreAlbum");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> findAll() {
		return this.mDAO.findAll(Album.class, "nombreAlbum", true);
	}

}
