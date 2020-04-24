package app;

import model.Site;
import model.Transport;

public class TestEmf {

	public static void main(String[] args) throws ClassNotFoundException {
		

		Transport t = new Transport("Cheval",0.07,30);
		Site.getInstance().getDaoTransport().insert(t);
		
		
		
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
