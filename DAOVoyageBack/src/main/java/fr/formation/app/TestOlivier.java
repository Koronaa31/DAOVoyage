package fr.formation.app;

import java.util.List;

import fr.formation.model.Cagnotte;
import fr.formation.model.Client;
import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;

public class TestOlivier {

	public static void main(String[] args) {

		Client init = (Client) Site.getInstance().getDaoUtilisateur().findById(3).get();
//		Client dest = (Client) Site.getInstance().getDaoUtilisateur().findById(4).get();
//		
//		Ville v1 = Site.getInstance().getDaoVille().findById(1).get();
//		Ville v2 = Site.getInstance().getDaoVille().findById(4).get();
//		Transport t = Site.getInstance().getDaoTransport().findById(2).get();
//		Voyage v = new Voyage(v1, v2, t, dest);
//		v.setStatut("Cagnotte");
		
		//Site.getInstance().creationCagnotte(v, init);
		//daoV.save(v);
		
//		Cagnotte c = new Cagnotte(v.getPrix(), v.getClient(), init, v);
//		daoC.save(c);
		

		Site.getInstance().archives(init);
		for(Cagnotte c : init.getCagnottesParticipant()) {
			System.out.println(c.getParticipants());
		}
	}
}
