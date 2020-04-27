package app;

import java.util.List;

import model.Cagnotte;
import model.Client;
import model.Site;
import model.Transport;
import model.Ville;
import model.Voyage;

public class TestOlivier {

	public static void main(String[] args) {
		

		Client init = (Client) Site.getInstance().getDaoUtilisateur().selectById(3);
//		Client dest = (Client) Site.getInstance().getDaoUtilisateur().selectById(4);
//		
//		Ville v1 = Site.getInstance().getDaoVille().selectById(1);
//		Ville v2 = Site.getInstance().getDaoVille().selectById(2);
//		Transport t = Site.getInstance().getDaoTransport().selectById(2);
//		Voyage v = new Voyage(v1, v2, t, dest);
//		System.out.println(v);
//		v.setStatut("");
//		
//		init.creationCagnotte(v);
//
		List<Cagnotte> liste = Site.getInstance().getDaoCagnotte().selectByParticipant(init);
		for(Cagnotte c : liste) {
			System.out.println(c);
		}
	}
}
