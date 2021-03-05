package minimarketdemo.controller.seguriades;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;

@Named
@SessionScoped
public class BeanSegModulos implements Serializable {

	@EJB
	private ManagerSeguridades mSeguridades;

	private List<SegModulo> listaModulos;

	public BeanSegModulos() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String actionCargarMenuModulos() {
		this.listaModulos = mSeguridades.findAllModulos();
		return "modulos";
	}

	public List<SegModulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<SegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}

	private static final long serialVersionUID = 1L;

}
