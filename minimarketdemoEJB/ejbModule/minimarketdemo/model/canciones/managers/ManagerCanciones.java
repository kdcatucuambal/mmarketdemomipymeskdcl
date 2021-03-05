package minimarketdemo.model.canciones.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.task.Cancion;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerCanciones
 */
@Stateless
@LocalBean
public class ManagerCanciones implements ICrud {

	/**
	 * Default constructor.
	 */

	@EJB
	private ManagerDAO mDAO;

	public ManagerCanciones() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Object newObject) {
		try {
			this.mDAO.insertar((Cancion) newObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Object idObject) throws Exception {
		try {
			this.mDAO.eliminar(Cancion.class, idObject);
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el dato: " + e.getMessage());
		}

	}

	@Override
	public void update(Object upObject) {
		try {
			this.mDAO.actualizar((Cancion) upObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Cancion findById(Object idObject) {
		Cancion cancion = null;
		try {
			cancion = (Cancion) this.mDAO.findById(Cancion.class, idObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cancion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cancion> findByWhere(String field, String value) {
		String where = "o.".concat(field).concat("=").concat("'").concat(value).concat("'");
		return this.mDAO.findWhere(Cancion.class, where, "nombreAlbum");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cancion> findAll() {
		return this.mDAO.findAll(Cancion.class, "tituloCancion", true);
	}

}
