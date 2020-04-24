package app;

import model.Site;
import model.Transport;
import model.Ville;

public class TestEmf {

	public static void main(String[] args) throws ClassNotFoundException {
		

		System.out.println(Site.getInstance().getDaoTransport().selectByNom("Avion"));
		
		
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
