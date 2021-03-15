package minimarketdemo.api.rest.thumano;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import minimarketdemo.controller.AdaptiveResponse;
import minimarketdemo.model.core.entities.ThmCargo;
import minimarketdemo.model.thumano.dtos.DTOThmCargo;
import minimarketdemo.model.thumano.dtos.DTOThmEmpleado;
import minimarketdemo.model.thumano.managers.ManagerTHumano;

@RequestScoped
@Path("thumano")
@Produces("application/json")
@Consumes("application/json")
public class ServiceRestThumano {

	@EJB
	private ManagerTHumano mThumano;

	@GET
	@Path("cargos")
	public Response findAllDTOThmCargo() {
		List<DTOThmCargo> cargos = this.mThumano.findAllDTOThmCargo();
		if (cargos.size() == 0) {
			return Response.status(Response.Status.OK)
					.entity(new AdaptiveResponse("No hay registros", "Tamaño de arreglo: " + cargos.size())).build();
		}
		return Response.status(Response.Status.OK).entity(cargos).build();
	}

	@GET
	@Path("cargos/{id}")
	public Response findDTOThmCargoById(@PathParam("id") int id) {
		Object response = null;
		Status status = null;
		try {
			ThmCargo cargo = this.mThumano.findByIdThmCargo(id);
			status = Response.Status.OK;
			response = new DTOThmCargo(id, cargo.getNombreCargo(), cargo.getRemuneracionMensual().doubleValue());
		} catch (Exception e) {
			response = new AdaptiveResponse("No se encontro el dato", e.getMessage());
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}

		return Response.status(status).entity(response).build();
	}

	@POST
	@Path("cargos")
	public Response createCargo(ThmCargo cargo) {
		Object response = null;
		Status status = null;
		try {
			this.mThumano.insertarCargo(cargo);
			status = Response.Status.OK;
			response = new AdaptiveResponse("Cargo creado con éxito", new DTOThmCargo(cargo.getIdThmCargo(),
					cargo.getNombreCargo(), cargo.getRemuneracionMensual().doubleValue()));
		} catch (Exception e) {
			status = Response.Status.INTERNAL_SERVER_ERROR;
			response = new AdaptiveResponse("Error al crear", e.getMessage());
		}

		return Response.status(status).entity(response).build();
	}

	@PUT
	@Path("cargos/{id}")
	public Response updateCargo(@PathParam("id") int id, ThmCargo cargo) {
		Object response = null;
		Status status = null;
		try {
			cargo.setIdThmCargo(id);
			this.mThumano.actualizarCargo(cargo);
			status = Response.Status.OK;
			response = new AdaptiveResponse("Cargo actualizado con éxito", new DTOThmCargo(cargo.getIdThmCargo(),
					cargo.getNombreCargo(), cargo.getRemuneracionMensual().doubleValue()));
		} catch (Exception e) {
			status = Response.Status.INTERNAL_SERVER_ERROR;
			response = new AdaptiveResponse("Error al actualizar, no existe el dato", e.getMessage());
		}

		return Response.status(status).entity(response).build();
	}

	@DELETE
	@Path("cargos/{id}")
	public Response deleteCargo(@PathParam("id") int id) {
		Object response = null;
		Status status = null;
		try {
			this.mThumano.eliminarCargo(id);
			status = Response.Status.OK;
			response = new AdaptiveResponse("Cargo eliminado con éxito", "Id de cargo eliminado -> " + id);
		} catch (Exception e) {
			status = Response.Status.INTERNAL_SERVER_ERROR;
			response = new AdaptiveResponse("Error al eliminar", e.getMessage());
		}

		return Response.status(status).entity(response).build();
	}

	@GET
	@Path("empleados")
	public Response findAllDTOThmEmpelado() {
		List<DTOThmEmpleado> responseEmpleados = this.mThumano.findAllDTOThmEmpleado();
		if (responseEmpleados.size() == 0) {
			return Response.status(Response.Status.OK)
					.entity(new AdaptiveResponse("No hay registros", "Tamaño de arreglo: " + responseEmpleados.size()))
					.build();
		}
		return Response.status(Response.Status.OK).entity(responseEmpleados).build();
	}

	@GET
	@Path("empleados/{id}")
	public Response findDTOThmEmpeladoById(@PathParam("id") int id) {
	
		Object response = null;
		Status status = null;
		try {
			status = Response.Status.OK;
			response = this.mThumano.findDTOThmEmpleadoByIdSegUsuario(id);
		} catch (Exception e) {
			status = Response.Status.INTERNAL_SERVER_ERROR;
			response = new AdaptiveResponse("Error de solicitud", "El usuario con id " + id + " no es un empleado activo válido");
		}

		if (response == null) {
			response = new AdaptiveResponse("Error de solicitud", "El usuario con id " + id + " no es un empleado activo válido");
		}
		
		return Response.status(status).entity(response).build();
	}

}
