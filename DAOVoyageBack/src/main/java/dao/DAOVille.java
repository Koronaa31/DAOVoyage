package dao;

import model.Ville;

public interface DAOVille extends DAO<Ville,Integer> {
	
	 public Ville selectByNom(String nom);

}
