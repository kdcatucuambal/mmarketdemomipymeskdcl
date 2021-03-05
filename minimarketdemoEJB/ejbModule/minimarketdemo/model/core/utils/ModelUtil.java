package minimarketdemo.model.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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

}
