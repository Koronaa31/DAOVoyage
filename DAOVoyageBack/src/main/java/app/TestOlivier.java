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
		Client dest = (Client) Site.getInstance().getDaoUtilisateur().selectById(4);
		Client part = (Client) Site.getInstance().getDaoUtilisateur().selectById(7);
		
		Ville vill = Site.getInstance().getDaoVille().selectByNom("Toulouse");
		System.out.println(vill);
		List<Ville> villes = Site.getInstance().getDaoVille().selectAll();
		
		System.out.println(villes.size());
		for(Ville v : villes) {
			System.out.println(v);
		}

		
	}
}
