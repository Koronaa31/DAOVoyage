package fr.formation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOCagnotte;
import fr.formation.dao.IDAOTransport;
import fr.formation.dao.IDAOUtilisateur;
import fr.formation.dao.IDAOVille;
import fr.formation.dao.IDAOVoyage;

@Service
public class Site {

	LinkedList<Voyage> voyage = new LinkedList<>();
	LinkedList<Voyage> panier = new LinkedList<>();
	private Connection connection = null;
//	private static Site _instance = null;

	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@Autowired
	private IDAOVille daoVille;
	
	@Autowired
	private IDAOTransport daoTransport;
	
	@Autowired
	private IDAOCagnotte daoCagnotte;
	
	@Autowired
	private IDAOVoyage daoVoyage;

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

	public IDAOUtilisateur getDaoUtilisateur() {
		return daoUtilisateur;
	}

	public void setDaoUtilisateur(IDAOUtilisateur daoUtilisateur) {
		this.daoUtilisateur = daoUtilisateur;
	}

	public IDAOVille getDaoVille() {
		return daoVille;
	}

	public void setDaoVille(IDAOVille daoVille) {
		this.daoVille = daoVille;
	}

	public IDAOTransport getDaoTransport() {
		return daoTransport;
	}

	public void setDaoTransport(IDAOTransport daoTransport) {
		this.daoTransport = daoTransport;
	}

	public IDAOCagnotte getDaoCagnotte() {
		return daoCagnotte;
	}

	public void setDaoCagnotte(IDAOCagnotte daoCagnotte) {
		this.daoCagnotte = daoCagnotte;
	}

	public IDAOVoyage getDaoVoyage() {
		return daoVoyage;
	}

	public void setDaoVoyage(IDAOVoyage daoVoyage) {
		this.daoVoyage = daoVoyage;
	}

//	public static Site getInstance() {
//		if(_instance==null) {
//			AnnotationConfigApplicationContext myContext = 
//					new AnnotationConfigApplicationContext(AppConfig.class);
//			
//			_instance = new Site();
//			_instance.daoTransport = myContext.getBean(IDAOTransport.class);
//			_instance.daoVoyage = myContext.getBean(IDAOVoyage.class);
//			_instance.daoCagnotte = myContext.getBean(IDAOCagnotte.class);
//			_instance.daoVille = myContext.getBean(IDAOVille.class);
//			_instance.daoUtilisateur = myContext.getBean(IDAOUtilisateur.class);
//		}
//		return _instance;
//	}

	public Utilisateur checkConnect(String login, String password) {
		return daoUtilisateur.findByLoginAndPassword(login, password);
	}

	public void inscription(Utilisateur c) {
		daoUtilisateur.save(c);
	}

	public Utilisateur checkMail(String adresseMail) {
		return daoUtilisateur.findByAdresseMail(adresseMail);
	}

	public void research(Ville v1){
		List<Ville> ville = daoVille.findAll();
		List<Transport> transport = daoTransport.findAll();

		for(Ville v2 : ville)
		{
			if(!v2.getNom().equals(v1.getNom()))
			{
				for (Transport t : transport)
				{
					Voyage voy = new Voyage(v1,v2,t);
					this.voyage.add(voy);
				}
			}
		}
	}

	public void research(Ville v1,Ville v2) {
		List<Transport> transport = daoTransport.findAll();
		for (Transport t : transport)
		{
			Voyage v = new Voyage(v1,v2,t);
			this.voyage.add(v);
		}
	}

	public void research(Ville v1,Ville v2,Transport t) {
		Voyage v = new Voyage(v1,v2,t);
		this.voyage.add(v);
	}

	public void choix(Voyage voy)
	{
		this.panier.add(voy);
		System.out.println("Panier garni !");
	}

	public void paiement () {
		System.out.println("Paiement effectu� ! Merci Jordan.");
		this.getPanier().clear();
	}
	
	public void ajoutVille(Ville v) {
		daoVille.save(v);
	}
	
	public void ajoutTransport(Transport t) {
		daoTransport.save(t);
	}

	public void listVilleDispo() {
		List<Ville> ville = daoVille.findAll();
		int i = 0;
		for (Ville v : ville) {
			i++;
			System.out.println(i+"- "+v.getNom());
		}
	}

	public void listTransportDispo() {
		List<Transport> transport = daoTransport.findAll();
		int i = 0;
		for (Transport t : transport) {
			i++;
			System.out.println(i+"- "+t.getNom());
		}
	}
	
	public void creationCagnotte(Voyage v, Client init) {
		Cagnotte c = new Cagnotte(v.getPrix(), v.getClient(), init, v);
		this.daoCagnotte.save(c);
	}
	
	public void participer(double somme, Cagnotte c, Client p) {
		double n = c.getSommeAPayer()-somme;
		c.setSommeAPayer(n);
		c.addParticipant(p);
		this.daoCagnotte.save(c);
	}
	
	public void archives(Client c) {
		c.setCagnottesDestinataire(daoCagnotte.findByDestinataire(c));
		c.setCagnottesInitiateur(daoCagnotte.findByInitiateur(c));
		c.setCagnottesParticipant(daoCagnotte.findByParticipant(c));
		noDoublon(c.getCagnottesDestinataire());
		noDoublon(c.getCagnottesInitiateur());
		noDoublon(c.getCagnottesParticipant());
	}
	
	public void noDoublon(List<Cagnotte> liste) {
		List<Client> part = new ArrayList<Client>();
		for(Cagnotte c : liste) {
			part = c.getParticipants();
			Set<Client> mySet = new HashSet<Client>(part);
			List<Client> myList = new ArrayList<Client>(mySet);
			c.setParticipants(myList);
			}
	}
	



	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/voyage","root","");
		return connection;
	}

}