package minimarketdemo.api.rest.auditoria;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.utils.ModelUtil;

@RequestScoped // Has no state
@Path("auditoria")
@Produces("application/json")
@Consumes("application/json")
public class ServiceRestAuditoria {

	@EJB
	private ManagerAuditoria mAuditoria;

	//ENdpoint /apirest/auditoria/bitacora
	@GET
	@Path(value = "bitacora")
	public List<AudBitacora> findBitacoraAyer() {
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(new Date(), -1), new Date());
	}
	
	

}
