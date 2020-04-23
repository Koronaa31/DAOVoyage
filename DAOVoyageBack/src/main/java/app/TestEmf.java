package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAOUtilisateur;
import dao.jpa.DAOJpa;
import model.Client;
import model.Site;

public class TestEmf {

	public static void main(String[] args) throws ClassNotFoundException {
		

		DAOUtilisateur daoC = Site.getInstance().getDaoUtilisateur();
		
		Client c = new Client("jojo", "jj", "a@a.a");
		Site.getInstance().getDaoUtilisateur().insert(c);
		
		
		
//		Medecin medecin = (Medecin) daoCompte.selectById(2);
//		
//		Patient patient = new Patient(487569354, "Potter", "Harry");
//		Visite visite = new Visite(medecin,patient);
//		
//		daoPatient.insert(patient);
//		daoVisite.insert(visite);



		//DAOJpa.close();
		
	
	
	}
	

}
