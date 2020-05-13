package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {

	public Utilisateur findByAdresseMail(String adresseMail);
	
	public Utilisateur findByLoginAndPassword(String login, String password);
	
	public Utilisateur findByLogin(String login);
}
