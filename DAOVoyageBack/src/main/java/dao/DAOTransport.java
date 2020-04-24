package dao;

import model.Transport;

public interface DAOTransport extends DAO<Transport,Integer> {
	
	 public Transport selectByNom(String nom);

}
