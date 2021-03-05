package minimarketdemo.model.seguridades.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.SegAsignacion;
import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.utils.ModelUtil;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerSeguridades Funcionalidades
 * principales
 * <ul>
 * <li>Verificación de credenciales de usuario</li>
 * <li>Asignación de modulos a un usuario</li>
 * </ul>
 */
@Stateless
@LocalBean
public class ManagerSeguridades {

	@EJB
	private ManagerDAO mDAO;

	@EJB
	private ManagerAuditoria mAud;

	/**
	 * Default constructor.
	 */
	public ManagerSeguridades() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Función de inicialización de usuarios
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void inicializarDemo() throws Exception {
		mAud.mostrarLog(getClass(), "inicializarDemo", "Inicialización de bdd demo");
		List<SegUsuario> listaUsuarios = mDAO.findAll(SegUsuario.class);
		for (SegUsuario u : listaUsuarios) {
			mAud.mostrarLog(getClass(), "inicializarDemo", "Eliminación de usuario: " + u.getCorreo());
			mDAO.eliminar(SegUsuario.class, u.getIdSegUsuario());
		}

		SegUsuario admin = new SegUsuario();

		admin.setActivo(true);
		admin.setApellidos("Catucuamba");
		admin.setNombres("Kevin");
		admin.setCorreo("kdcatucuambal@utn.edu.ec");
		admin.setClave("kevin");
		admin.setCodigo("kevin");
		mDAO.insertar(admin);
		mAud.mostrarLog(getClass(), "inicializarDemo", "Administrador creado: ");
		// Inicialización de módulos
		SegModulo modulo = new SegModulo();
		modulo.setNombreModulo("Seguridades");
		modulo.setRutaAcceso("seguridades");
		mDAO.insertar(modulo);
		modulo = new SegModulo();
		modulo.setNombreModulo("Auditoria");
		modulo.setRutaAcceso("auditoria");
		mDAO.insertar(modulo);
		mAud.mostrarLog(getClass(), "inicializarDemo", "Módulos creados");
		// alter sequence seg_usuario_id_seg_usuario_seq restart
		// Asignación de accesos
		// Long sequence =
		// this.mDAO.obtenerSecuenciaPostgresql("seg_usuario_id_seg_usuario_seq");
		this.asignarModulo(1, 1);
		this.asignarModulo(1, 2);
		mAud.mostrarLog(getClass(), "inicializarDemo", "Inicialización de bdd demo terminada.");

	}

	/**
	 * Función de autenticación por medio de credenciales
	 * 
	 * @param idSeguridad
	 * @param clave
	 * @return la ruta de accesso al sistema
	 * @throws Exception
	 */
	public LoginDTO login(int idSeguridad, String clave) throws Exception {
		if (ModelUtil.isEmpty(clave)) {
			mAud.mostrarLog(getClass(), "login", "Debe indicar una clave: " + idSeguridad);
			throw new Exception("Debe indicar una clave");
		}
		SegUsuario usuario = (SegUsuario) mDAO.findById(SegUsuario.class, idSeguridad);
		if (usuario == null) {
			mAud.mostrarLog(getClass(), "login", "No existe usuario: " + idSeguridad);
			throw new Exception("Error de credenciales");
		}

		if (usuario.getClave().equals(clave)) {
			mAud.mostrarLog(getClass(), "login", "Login exitoso: " + idSeguridad);
			// CREATE DTO
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setIdSegUsuario(usuario.getIdSegUsuario());
			loginDTO.setCorreo(usuario.getCorreo());
			// Get list of modules that have access
			String consulta = "o.segUsuario.idSegUsuario=" + usuario.getIdSegUsuario();
			@SuppressWarnings("unchecked")
			List<SegAsignacion> listaAsignaciones = mDAO.findWhere(SegAsignacion.class, consulta, null);
			for (SegAsignacion asig : listaAsignaciones) {
				SegModulo modulo = asig.getSegModulo();
				loginDTO.getListaModulos().add(modulo);
			}
			return loginDTO;
		}
		mAud.mostrarLog(getClass(), "login", "No coincide la clave: " + idSeguridad);
		throw new Exception("Error en credenciales");
	}

	/**
	 * Permite asignar el acceso a un módulo del inventario del sitema
	 * 
	 * @param idSegUsuario
	 * @param idSegModulo
	 */

	public void asignarModulo(int idSegUsuario, int idSegModulo) throws Exception {
		// Buscar objetos primarios
		SegUsuario usuario = (SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
		SegModulo modulo = (SegModulo) mDAO.findById(SegModulo.class, idSegModulo);
		// crear relacion
		SegAsignacion asignacion = new SegAsignacion();
		asignacion.setSegModulo(modulo);
		asignacion.setSegUsuario(usuario);
		mDAO.insertar(asignacion);
		mAud.mostrarLog(getClass(), "asignarModulo", "Modulo " + idSegModulo + " asignado a usuario " + idSegUsuario);
	}

	public void accesoNoPermitido(int idSegUsuario, String ruta) {
		mAud.mostrarLog(getClass(), "accesoNoPermitido",
				"Usuario " + idSegUsuario + " intento no autorizado a " + ruta);
	}

	public void cerrarSesion(int idSeguridad) {
		mAud.mostrarLog(getClass(), "cerrarSesion()", "Cerrrar sesión usuario: " + idSeguridad);
	}

	@SuppressWarnings("unchecked")
	public List<SegUsuario> findAllUsuarios() {
		return mDAO.findAll(SegUsuario.class, "apellidos");
	}

	@SuppressWarnings("unchecked")
	public List<SegModulo> findAllModulos() {
		return mDAO.findAll(SegModulo.class, "nombreModulo");
	}

}
