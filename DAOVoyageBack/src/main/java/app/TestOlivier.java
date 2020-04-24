package app;

import java.util.List;

import model.Site;
import model.Ville;

public class TestOlivier {

	public static void main(String[] args) {
		
		
		Ville vill = Site.getInstance().getDaoVille().selectByNom("Toulouse");
		System.out.println(vill);
		List<Ville> villes = Site.getInstance().getDaoVille().selectAll();
		
		System.out.println(villes.size());
		for(Ville v : villes) {
			System.out.println(v);
		}

		
	}

}
