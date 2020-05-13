package fr.formation.app;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.dao.IDAOVille;
import fr.formation.model.Ville;

public class TestOlivier {
	
	@Autowired
	static IDAOVille daoVille;
	
	public static void main(String[] args) {
		
		
		Ville v1 = daoVille.findByNom("Toulouse");
	}
}
