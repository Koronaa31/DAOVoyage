package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dao.DAOTransport;
import dao.DAOUtilisateur;
import dao.DAOVille;
import dao.jdbc.DAOVilleJDBC;
import dao.jpa.DAOTransportJPA;
import dao.jpa.DAOUtilisateurJPA;
import dao.jpa.DAOVilleJPA;

public class Site {

	LinkedList<Voyage> voyage = new LinkedList<>();
	LinkedList<Voyage> panier = new LinkedList<>();
	private Connection connection = null;
	private static Site _instance = null;

	private static DAOUtilisateur daoC = null;
	private static DAOVille daoV = null;
	private static DAOTransport daoT = null;

	//-----------------------------------------------------//
	//-----------------------------------------------------//
	//-----------------------------------------------------//
	//-----------------------------------------------------//

	private Site() {
	}

	//-----------------------------------------------------//
	//-----------------------------------------------------//
	//-----------------------------------------------------//
	//-----------------------------------------------------//    

	public LinkedList<Voyage> getVoyage() {
		return voyage;
	}

	public void setVoyage(LinkedList<Voyage> voyage) {
		this.voyage = voyage;
	}

	public LinkedList<Voyage> getPanier() {
		return panier;
	}

	public void setPanier(LinkedList<Voyage> panier) {
		this.panier = panier;
	}

	//-----------------------------------------------------//
	//-----------------------------------------------------//
	//-----------------------------------------------------//
	//-----------------------------------------------------//

	public static DAOUtilisateur getDaoUtilisateur() {
		if (daoC == null) {daoC = new DAOUtilisateurJPA();}	//Changer ici pour passer en JPA/JDBC
		return daoC;
	}

	public static DAOVille getDaoVille() {
		if (daoV == null) {daoV = new DAOVilleJPA();}	//Changer ici pour passer en JPA/JDBC
		return daoV;
	}
	
	public static DAOTransport getDaoTransport() {
		if (daoT == null) {daoT = new DAOTransportJPA();}	//Changer ici pour passer en JPA/JDBC
		return daoT;
	}

	public static Site getInstance() {
		if(_instance==null) {
			_instance = new Site();
		}
		return _instance;
	}

	public Utilisateur checkConnect(String login, String password) {
		return getDaoUtilisateur().selectByLoginPassword(login, password);
	}

	public void inscription(Utilisateur c) {
		getDaoUtilisateur().insert(c);
	}

	public Utilisateur checkMail(String adresseMail) {
		return getDaoUtilisateur().selectByAdresseMail(adresseMail);
	}

	public void research(Ville v1){
		List<Ville> ville = getDaoVille().selectAll();
		List<Transport> transport = getDaoTransport().selectAll();

		for(Ville v2 : ville)
		{
			if(!v2.getNom().equals(v1.getNom()))
			{
				for (Transport t : transport)
				{
					Voyage voy = new Voyage(v1,v2,t);
					Site.getInstance().voyage.add(voy);
				}
			}
		}
	}

	public void research(Ville v1,Ville v2) {
		List<Transport> transport = getDaoTransport().selectAll();
		for (Transport t : transport)
		{
			Voyage v = new Voyage(v1,v2,t);
			Site.getInstance().voyage.add(v);
		}
	}

	public void research(Ville v1,Ville v2,Transport t) {
		Voyage v = new Voyage(v1,v2,t);
		Site.getInstance().voyage.add(v);
	}

	public void choix(Voyage voy)
	{
		Site.getInstance().panier.add(voy);
		System.out.println("Panier garni !");
	}

	public void paiement () {
		System.out.println("Paiement effectu� ! Merci Jordan.");
		Site.getInstance().getPanier().clear();
	}
	
	public void ajoutVille(Ville v) {
		getDaoVille().insert(v);
	}
	
	public void ajoutTransport(Transport t) {
		getDaoTransport().insert(t);
	}

	public void listVilleDispo() {
		List<Ville> ville = getDaoVille().selectAll();
		int i = 0;
		for (Ville v : ville) {
			i++;
			System.out.println(i+"- "+v.getNom());
		}
	}

	public void listTransportDispo() {
		List<Transport> transport = getDaoTransport().selectAll();
		int i = 0;
		for (Transport t : transport) {
			i++;
			System.out.println(i+"- "+t.getNom());
		}
	}


	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyage","root","");
		return connection;
	}

}