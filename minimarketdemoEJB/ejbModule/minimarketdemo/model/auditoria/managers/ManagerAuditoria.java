package minimarketdemo.model.auditoria.managers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerAuditoria
 */
@Stateless
@LocalBean
public class ManagerAuditoria {
	
	@EJB
	private ManagerDAO mDAO;
	/**
	 * Default constructor.
	 */
	public ManagerAuditoria() {

	}

	/**
	 * Método básico para mostrar mensaje de depuración
	 * @param clase
	 * @param nombreMetodo
	 * @param mensaje
	 */
	@SuppressWarnings("rawtypes")
	public void mostrarLog(Class clase, String nombreMetodo, String mensaje) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(
				format.format(new Date()) + "[" + clase.getSimpleName() + "/" + nombreMetodo + "]: " + mensaje);
		
		AudBitacora pista=new AudBitacora();
		pista.setDescripcionEvento(mensaje);
		pista.setDireccionIp("localhost");
		Timestamp tiempo= new Timestamp(System.currentTimeMillis());
		pista.setFechaEvento(tiempo);
		pista.setIdUsuario("anonimo");
		pista.setNombreClase(clase.getSimpleName());
		pista.setNombreMetodo(nombreMetodo);
		try {
			mDAO.insertar(pista);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
