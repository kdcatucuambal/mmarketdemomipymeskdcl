package minimarketdemo.model.inventario.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.canciones.managers.ICrud;
import minimarketdemo.model.core.entities.exam.Movimiento;
import minimarketdemo.model.core.entities.exam.Producto;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerInventario
 */
@Stateless
@LocalBean
public class ManagerInventario implements ICrud {

	@EJB
	private ManagerDAO mDAO;

	public ManagerInventario() {

	}

	@Override
	public void create(Object newObject) {
		try {
			this.mDAO.insertar((Producto) newObject);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createMovimiento(Producto p, Integer cantidad) {
		try {
			Movimiento m = new Movimiento();
			m.setCantidad(cantidad);
			m.setInvProducto(p);
			this.mDAO.insertar(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Object idObject) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object upObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Producto findById(Object idObject) {
		Producto p = null;
		try {
			p = (Producto) this.mDAO.findById(Producto.class, idObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findByWhere(String field, String value) {
		String where = "o.".concat(field).concat("=").concat("'").concat(value).concat("'");
		return this.mDAO.findWhere(Producto.class, where, "nombre");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findAll() {
		return this.mDAO.findAll(Producto.class, "nombre", true);
	}

	public Integer calcularStock(Producto p) {
		Integer stock = 0;
		for (Movimiento m : p.getInvMovimientos()) {
			stock = stock + m.getCantidad();
		}
		return stock;
	}

}
