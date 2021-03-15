package minimarketdemo.model.thumano.dtos;

import java.math.BigDecimal;
import java.util.List;

public class DTOThmEmpleado {

	private String nombres;
	private String apellidos;
	private String correo;
	private String cargo;
	private BigDecimal remuneracionMensual;
	List<DTOThmRolPago> rolesPago;

	public DTOThmEmpleado(
			String nombres, 
			String apellidos, 
			String correo, String 
			cargo, 
			BigDecimal remuneracionMensual,
			List<DTOThmRolPago> rolesPago) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.cargo = cargo;
		this.remuneracionMensual = remuneracionMensual;
		this.rolesPago = rolesPago;
	}

	public DTOThmEmpleado(String nombres, String apellidos, String correo, String cargo,
			BigDecimal remuneracionMensual) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.cargo = cargo;
		this.remuneracionMensual = remuneracionMensual;

	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getRemuneracionMensual() {
		return remuneracionMensual;
	}

	public void setRemuneracionMensual(BigDecimal remuneracionMensual) {
		this.remuneracionMensual = remuneracionMensual;
	}

	public List<DTOThmRolPago> getRolesPago() {
		return rolesPago;
	}

	public void setRolesPago(List<DTOThmRolPago> rolesPago) {
		this.rolesPago = rolesPago;
	}

}
