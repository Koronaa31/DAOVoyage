package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Transport;

public interface IDAOTransport extends JpaRepository<Transport, Integer> {

	public Transport findByNom(String nom);
}
