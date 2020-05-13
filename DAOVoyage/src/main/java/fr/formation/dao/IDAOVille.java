package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Ville;

public interface IDAOVille extends JpaRepository<Ville, Integer> {
	
	public Ville findByNom(String nom);
}
