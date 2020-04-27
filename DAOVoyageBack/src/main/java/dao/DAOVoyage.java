package dao;

import java.util.List;

import model.Client;
import model.Voyage;

public interface DAOVoyage extends DAO<Voyage,Integer> {
	
	public List<Voyage> selectByClient(Client client, String Statut);
	
}