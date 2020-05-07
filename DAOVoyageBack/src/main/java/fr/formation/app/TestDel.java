package fr.formation.app;

import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;

public class TestDel {

	@Autowired
	private static Site site;
	
	public static void main(String[] args) {

		Ville v1 = site.getDaoVille().findByNom("Toulouse");
		System.out.println(v1);
		
		Ville v2 = site.getDaoVille().findByNom("Nice");
		Transport t = site.getDaoTransport().findByNom("Cheval");
		
		Voyage voy = new Voyage(v1,v2,t);
		
		System.out.println(voy);
		
	}
}
