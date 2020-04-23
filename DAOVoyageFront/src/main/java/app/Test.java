package app;

import dao.jdbc.DAOClientJDBC;
import model.Utilisateur;
import model.Site;

public class Test {

	public static void main(String[] args) {
		Utilisateur c = Site.getInstance().checkConnect("Koro", "sopra");
		System.out.println(c);
	}

}
