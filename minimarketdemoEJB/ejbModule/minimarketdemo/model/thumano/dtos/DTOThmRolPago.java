package minimarketdemo.model.thumano.dtos;

import java.math.BigDecimal;

public class DTOThmRolPago {

	private String periodo;
	private BigDecimal subtotalIngresos;
	private BigDecimal subtotalIngresosAdicionales;
	private BigDecimal subtotalEgresos;
	private BigDecimal pagoTotal;

	public DTOThmRolPago(String periodo, BigDecimal subtotalIngresos, BigDecimal subtotalIngresosAdicionales,
			BigDecimal subtotalEgresos, BigDecimal pagoTotal) {

		this.periodo = periodo;
		this.subtotalIngresos = subtotalIngresos;
		this.subtotalIngresosAdicionales = subtotalIngresosAdicionales;
		this.subtotalEgresos = subtotalEgresos;
		this.pagoTotal = pagoTotal;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getSubtotalIngresos() {
		return subtotalIngresos;
	}

	public void setSubtotalIngresos(BigDecimal subtotalIngresos) {
		this.subtotalIngresos = subtotalIngresos;
	}

	public BigDecimal getSubtotalIngresosAdicionales() {
		return subtotalIngresosAdicionales;
	}

	public void setSubtotalIngresosAdicionales(BigDecimal subtotalIngresosAdicionales) {
		this.subtotalIngresosAdicionales = subtotalIngresosAdicionales;
	}

	public BigDecimal getSubtotalEgresos() {
		return subtotalEgresos;
	}

	public void setSubtotalEgresos(BigDecimal subtotalEgresos) {
		this.subtotalEgresos = subtotalEgresos;
	}

	public BigDecimal getPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(BigDecimal pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

}
