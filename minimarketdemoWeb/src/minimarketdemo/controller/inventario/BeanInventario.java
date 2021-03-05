package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.exam.Movimiento;
import minimarketdemo.model.core.entities.exam.Producto;
import minimarketdemo.model.inventario.managers.ManagerInventario;

@Named
@SessionScoped
public class BeanInventario implements Serializable {

	@EJB
	private ManagerInventario mI;

	private List<Producto> productos;
	private Producto producto;
	private Movimiento movimiento;
	private Integer cantidad;

	public BeanInventario() {

	}

	public String actionLoadProductos() {
		this.productos = this.mI.findAll();
		return "productos" + "?faces-redirect=true";
	}

	public Integer calcularStock(Producto p) {
		return this.mI.calcularStock(p);
	}

	public Integer calcularStock() {
		if (this.producto != null) {
			Integer response = this.mI.calcularStock(this.producto);
			return -1 * response;
		}
		return 0;
	}

	public void openNew() {
		this.producto = new Producto();
	}

	public void saveProducto() {
		if (this.producto.getIdProducto() == null) {
			this.mI.create(this.producto);
			JSFUtil.crearMensajeINFO("Producto nuevo: " + this.producto.getIdProducto());
		}
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		this.productos = this.mI.findAll();
	}

	public void saveMovimiento() {
		if (this.cantidad != 0) {
			this.mI.createMovimiento(this.producto, this.cantidad);
			JSFUtil.crearMensajeINFO("Movimiento registrado: ");
			PrimeFaces.current().executeScript("PF('manageProductDialog2').hide()");
			this.productos = this.mI.findAll();
			return;
		}
		JSFUtil.crearMensajeWARN("No se guardó movimiento de 0");

	}

	public void actionSetProducto(Producto p) {
		this.producto = p;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	private static final long serialVersionUID = 1L;

}
