package fr.formation.dao;

import java.util.List;

public interface IDAO<T,K> {
	
	public void save(T t);
	
	public T findById(K id);
	
	public List<T> findAll();
		
	public void deleteById(K id);
}
