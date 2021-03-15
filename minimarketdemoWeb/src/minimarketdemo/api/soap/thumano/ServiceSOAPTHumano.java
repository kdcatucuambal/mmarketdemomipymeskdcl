package minimarketdemo.api.soap.thumano;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import minimarketdemo.model.core.entities.ThmCargo;
import minimarketdemo.model.thumano.dtos.DTOThmCargo;
import minimarketdemo.model.thumano.managers.ManagerTHumano;

@WebService(serviceName = "ServiceSOAPTHumano")
public class ServiceSOAPTHumano {

	@EJB
	private ManagerTHumano mTHumano;

	@WebMethod(operationName = "findAllThmCargo")
	public List<DTOThmCargo> findAllThmCargo() {
		return this.mTHumano.findAllDTOThmCargo();
	}

	public DTOThmCargo findThmCargoById(@WebParam(name = "idCargo") Integer idCargo) {
		DTOThmCargo response = null;
		try {
			ThmCargo cargo = this.mTHumano.findByIdThmCargo(idCargo);
			response = new DTOThmCargo(cargo.getIdThmCargo(), cargo.getNombreCargo(),
					cargo.getRemuneracionMensual().doubleValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = null;
		}
		return response;
	}

	@WebMethod(operationName = "createCargo")
	public String createCargo(@WebParam(name = "cargo") DTOThmCargo cargo) {
		String message = "";
		try {
			ThmCargo cargoNew = new ThmCargo();
			cargoNew.setNombreCargo(cargo.getNombreCargo());
			cargoNew.setRemuneracionMensual(new BigDecimal(cargo.getRemuneracionMensual()));
			this.mTHumano.insertarCargo(cargoNew);
			message = "Se ha insertado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "Ocurrio un error: " + e.getMessage();
		}
		return message;
	}

	@WebMethod(operationName = "updateCargo")
	public String updateCargo(@WebParam(name = "cargo") DTOThmCargo cargo) {
		String message = "";
		try {
			ThmCargo cargoFound = this.mTHumano.findByIdThmCargo(cargo.getIdThmCargo());
			cargoFound.setNombreCargo(cargo.getNombreCargo());
			cargoFound.setRemuneracionMensual(new BigDecimal(cargo.getRemuneracionMensual()));
			this.mTHumano.actualizarCargo(cargoFound);
			message = "Se ha actualizado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "Ocurrio un error: " + e.getMessage();
		}
		return message;
	}

	@WebMethod(operationName = "deleteCargo")
	public String deleteCargo(@WebParam(name = "idCargo") Integer idCargo) {
		String message = "";
		try {
			this.mTHumano.eliminarCargo(idCargo);
			message = "Se ha eliminado correctamente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "Ocurrio un error: " + e.getMessage();
		}
		return message;
	}

}
