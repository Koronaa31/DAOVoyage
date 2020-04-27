package dao;

import java.util.List;

import model.Cagnotte;
import model.Client;

public interface DAOCagnotte extends DAO<Cagnotte,Integer> {
	
	public List<Cagnotte> selectByDestinataire(Client destinataire);
	
	public List<Cagnotte> selectByInitiateur(Client initiateur);
	
	public List<Cagnotte> selectByParticipant(Client participant);
	
	public List<Cagnotte> selectByDestinataireLogin(String login);

}
