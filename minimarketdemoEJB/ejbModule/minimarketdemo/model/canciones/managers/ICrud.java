package minimarketdemo.model.canciones.managers;

import java.util.List;

public interface ICrud {

	public void create(Object newObject);

	public void delete(Object idObject) throws Exception;

	public void update(Object upObject);

	public Object findById(Object idObject);

	@SuppressWarnings("rawtypes")
	public List findByWhere(String field, String value);

	@SuppressWarnings("rawtypes")
	public List findAll();

}
