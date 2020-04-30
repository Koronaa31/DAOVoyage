package app;

import java.util.List;

import dao.jpa.DAOJpa;
import model.Client;
import model.Site;
import model.Transport;
import model.Ville;
import model.Voyage;

public class TestAntho {

	public static void main(String[] args) {
/*
		List<Ville> villes = Site.getInstance().getDaoVille().selectAll();
        System.out.println(villes.size());

        //Utilisateur utilisateur = new Client("antho", "antho","antho@antho.fr");				//ajouter un client
        //Site.getInstance().getDaoUtilisateur().insert(utilisateur);							//marche
        
        
        List<Utilisateur> utilisateurs = Site.getInstance().getDaoUtilisateur().selectAll();	//marche
        for(Utilisateur u : utilisateurs) {
        System.out.println(u);
        }
        
        Ville ville1 = Site.getInstance().getDaoVille().selectById(1);
        Ville ville2 = Site.getInstance().getDaoVille().selectById(2);
        Transport transport = Site.getInstance().getDaoTransport().selectById(2);
        Client client = (Client) Site.getInstance().getDaoUtilisateur().selectById(2);
        System.out.println(client);
        
        Voyage voyage = new Voyage(ville1,ville2,transport);
        voyage.setStatut("Commande");
        voyage.setClient(client);
        System.out.println(voyage);
        
        Site.getInstance().getDaoVoyage().insert(voyage);
        */
		
		String statut = "Commande";
		Client client = (Client) Site.getInstance().getDaoUtilisateur().selectById(3);
		List<Voyage> loadPanier = Site.getInstance().getDaoVoyage().selectByClient(client, statut);
		System.out.println(loadPanier);
		
        DAOJpa.close();
	}
}