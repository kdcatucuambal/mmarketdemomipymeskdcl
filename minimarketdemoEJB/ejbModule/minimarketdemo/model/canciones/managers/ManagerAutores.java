package minimarketdemo.model.canciones.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.task.Autor;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerAutores
 */
@Stateless
@LocalBean
public class ManagerAutores implements ICrud {

	/**
	 * Default constructor.
	 */
	@EJB
	private ManagerDAO mDAO;

	public ManagerAutores() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public void crear(Autor autor) { try { this.mDAO.insertar(autor); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void crear(String nombre, String pais, String foto) { Autor autor =
	 * new Autor(); autor.setNombreAutor(nombre); autor.setFotoAutor(foto);
	 * autor.setPaisAutor(pais); try { this.mDAO.insertar(autor); } catch (Exception
	 * e) { e.printStackTrace(); } }
	 */

	/*
	 * public void eliminar(Integer id) { try { this.mDAO.eliminar(Autor.class, id);
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 * 
	 * public void actualizar(Autor autor) { try { this.mDAO.actualizar(autor); }
	 * catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } }
	 */

	@Override
	public void create(Object newObject) {
		try {
			this.mDAO.insertar((Autor) newObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Object idObject) throws Exception {
		try {
			this.mDAO.eliminar(Autor.class, idObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("No se pudo eliminar el dato: " + e.getMessage());
		}

	}

	@Override
	public void update(Object upObject) {
		try {
			this.mDAO.actualizar((Autor) upObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Autor findById(Object idObject) {

		Autor autor = null;
		try {
			autor = (Autor) this.mDAO.findById(Autor.class, idObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Autor> findByWhere(String field, String value) {
		String where = "o.".concat(field).concat("=").concat("'").concat(value).concat("'");
		return this.mDAO.findWhere(Autor.class, where, "nombreAutor");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Autor> findAll() {
		return this.mDAO.findAll(Autor.class, "nombreAutor", true);
	}

}
