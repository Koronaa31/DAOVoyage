package app;

import dao.jdbc.DAOClientJDBC;
import fr.formation.model.Site;
import fr.formation.model.Utilisateur;

public class Test {

	public static void main(String[] args) {
		Utilisateur c = Site.getInstance().checkConnect("Koro", "sopra");
		System.out.println(c);
	}

}
