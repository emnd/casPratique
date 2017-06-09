package casPratique.metier.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T,PK extends Serializable> {

	T find(PK id);
	
	List<T> findAll();
	
	void create(T obj);
	
	T update(T obj);
	
	void delete(T obj);
	
	void delete(PK id);
	
	
}
