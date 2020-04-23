package dao;

import model.Utilisateur;

public interface DAOUtilisateur extends DAO<Utilisateur,Integer> {
	
	public Utilisateur selectByAdresseMail(String adresseMail);
	
	public Utilisateur selectByLoginPassword(String login, String password);
	
}
