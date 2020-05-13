package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Client;
import fr.formation.model.Voyage;

public interface IDAOVoyage extends JpaRepository<Voyage, Integer> {

	
	public List<Voyage> findByClientAndStatut(Client client, String Statut);
}
