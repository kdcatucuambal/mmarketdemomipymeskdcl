package minimarketdemo.model.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Clase utilitaria para la campa modelo
 * @author kevincatu
 *
 */
public class ModelUtil {
	
	
	/**
	 * Verifica si una cadena es igual a null o tiene longitud cero
	 * @param value Valor a ser evaluado
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get current year
	 * @return current year integer
	 */
	public static int getCurrentYear() {
		Date currentDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		int currentYear = Integer.parseInt(format.format(currentDate));
		return currentYear;
	}
	
	/**
	 * Devuelve el valor del anio actual.
	 * @return valor entero del anio actual.
	 */
	public static int getAnioActual() {
		Date fechaActual=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("yyyy");
		int anioActual=Integer.parseInt(formato.format(fechaActual));
		return anioActual;
	}
	
	/**
	 * Adiciona o resta dias a una fecha de tipo Date.
	 * @param date la fecha original.
	 * @param days el numero de dias a sumar o restar.
	 * @return la fecha resultante.
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
	
	public static String SEQUENCE_THCARGOS = "thm_cargo_id_thm_cargo_seq";

}
