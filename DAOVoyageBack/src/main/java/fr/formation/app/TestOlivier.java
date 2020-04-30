package fr.formation.app;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.formation.config.AppConfig;
import fr.formation.dao.IDAOUtilisateur;
import fr.formation.model.Cagnotte;
import fr.formation.model.Client;
import fr.formation.model.Site;
import fr.formation.model.Transport;
import fr.formation.model.Ville;
import fr.formation.model.Voyage;

public class TestOlivier {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext myContext = 
				new AnnotationConfigApplicationContext(AppConfig.class);

		Client init = (Client) myContext.getBean(IDAOUtilisateur.class).findById(2).get();
		System.out.println(init);
		
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
//		init.archives();
//		for(Cagnotte c : init.getCagnottesParticipant()) {
//			System.out.println(c.getParticipants());
//		}
	}
}
