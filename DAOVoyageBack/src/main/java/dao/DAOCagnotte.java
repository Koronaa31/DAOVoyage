package dao;

import java.util.List;

import model.Cagnotte;
import model.Client;

public interface DAOCagnotte extends DAO<Cagnotte,Integer> {
	
	public List<Cagnotte> selectByDestinataire(Client idDestinataire);
	
	public List<Cagnotte> selectByInitiateur(Client idInitiateur);
	
	public List<Cagnotte> selectByParticipant(Client idParticipants);

}
