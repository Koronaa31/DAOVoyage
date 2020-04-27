package app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.jdbc.DAOClientJDBC;
import dao.jdbc.DAOVilleJDBC;
import model.Utilisateur;
import model.Client;
import model.Admin;
import model.Site;
import model.Transport;
import model.Ville;
import model.Voyage;

public class Test {

	static Utilisateur c = null;

	public static String saisieString (String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}

	public static int saisieInt (String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();
	}

	public static double saisieDouble (String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}

	public static void main(String[] args) {

		accueil();
		System.out.println();

	}

	public static void accueil() {
		c = new Admin();
		System.out.println("Bienvenue sur LE site de voyage.");
		System.out.println("Connectez-vous pour profitez d'une remise de 20% sur tous vos voyages.");
		System.out.println("1 - Pas encore inscrit ? Faites-le au plus vite !");
		System.out.println("2 - Se connecter.");
		System.out.println("3 - Faire des achats sans me connecter.");
		System.out.println("4 - Quitter.");
		int choix = saisieInt("\nChoisir un menu.");

		switch (choix) {
		case 1 : inscription(); break;
		case 2 : connect(); break;
		//case 3 : recherche(); break;
		case 4 : Site.getInstance().getPanier().clear(); System.exit(0); break;
		default : accueil();
		}
	}

	private static void connect() {
		String login = saisieString("Saisir un login.");
		String password = saisieString("Saisir un mot de passe.");
		c = Site.getInstance().checkConnect(login, password);
		if (c == null) {System.out.println("Identifiants invalides."); accueil();}
		//else {recherche();}
	}

	public static void inscription() {
		DAOClientJDBC daoC = new DAOClientJDBC();
		String mail = saisieString("Saisir adresse mail.");
		c = daoC.selectByAdresseMail(mail);
		if (c == null) {
			System.out.println("Adresse mail correcte.");
			String login = saisieString("Saisir un login.");
			String password = saisieString("Saisir un mot de passe.");
			daoC.inscription(login, password, mail);
			System.out.println("Insciption validée. Vous pouvez effectuer vos premiers achats.");
			c = Site.getInstance().checkConnect(login, password);
			//recherche();
		} else {
			System.out.println("Cette adresse mail est déjà utilisée. Login : "+((Client) c).getLogin());
			connect();
		}	
	}

//	public static void recherche() {
//		Site.getInstance().getVoyage().clear();
//		Ville v1 = null;
//		Ville v2 = null;
//		Transport t = null;
//		String choixVille2 = null;
//		String choixTransport = null;
//		int i = 0;
//		double somme = 0;
//
//		do  {
//			System.out.println("\nListe des villes de notre programme de voyage :");
//			Site.getInstance().listVilleDispo();
//			v1 = saisieVille("de départ.");
//		} while (v1 == null);
//
//		choixVille2 = saisieString("\nVoulez-vous choisir une ville d'arrivée ? (O/N)");
//		if (choixVille2.equals("O")) {
//			v2 = null;
//			do  {
//				System.out.println("\nListe des villes de notre programme de voyage :");
//				Site.getInstance().listVilleDispo();
//				v2 = saisieVille("d'arrivée.");
//			} while (v2 == null);
//			choixTransport = saisieString("\nVoulez-vous choisir un moyen de transport ? (O/N)");
//			if (choixTransport.equals("O")) {
//				t = null;
//				do  {
//					System.out.println("\nListe des moyens de transport :");
//					Transport[] listeTransport = Transport.values();
//					System.out.println(Arrays.toString(listeTransport));
//					String transport = saisieString("");
//					try {t = Transport.valueOf(transport);}
//					catch(IllegalArgumentException e) {System.out.println("Pas de moyen de transport avec ce nom !");}
//				} while (t == null);
//			}
//		}
//
//		if (choixVille2.equals("O") && choixTransport.equals("O")) {
//			Site.getInstance().research(v1, v2, t);
//		} else if (choixVille2.equals("O") && choixTransport.equals("N")) {
//			Site.getInstance().research(v1, v2);
//		} else {Site.getInstance().research(v1);}
//		
//		System.out.println("\nRésultats de la recherche :");
//		for (Voyage v : Site.getInstance().getVoyage()) {
//			i++;
//			System.out.println(i+"- "+v.getV1().getNom()+" - "+v.getV2().getNom()+" // Transport : "+v.getT().name()+" // Prix : "+(int) v.getPrix()+"€. // Durée : "+v.getDuree()+"h.");
//		}
//
//		String s = saisieString("\nVoulez-vous choisir un des voyages à ajouter au panier ? (O/R (refaire une recherche) /Q (quitter))");
//		if (s.equals("O")) {
//			int n = saisieInt("Numéro du voyage ?");
//			Voyage vChoisi = Site.getInstance().getVoyage().get(n-1);
//			Site.getInstance().choix(vChoisi);
//		} else if (s.equals("R")) {recherche();}
//		else {accueil();}
//
//		s = saisieString("Voulez-vous continuer les recherches ? (O/N)");
//		if (s.equals("O")) {
//			recherche();
//		} else {
//			i = 0;
//			System.out.println("Panier actuel : ");
//			for (Voyage v : Site.getInstance().getPanier()) {
//				i++;
//				System.out.println(i+"- "+v.getV1().getNom()+" - "+v.getV2().getNom()+" // Transport : "+v.getT().name()+" // Prix : "+(int) v.getPrix()+"€. // Durée : "+v.getDuree()+"h.");
//				somme = somme + v.getPrix();
//			}
//			System.out.println("Prix total : "+(int) somme+"€.");
//			if (c instanceof Client) {
//				somme = somme-somme*20/100;
//				System.out.println("Remise de 20%. Nouveau prix :"+(int) somme+"€.");
//			}
//			String s2 = saisieString("Voulez-vous que Jordan paye pour votre voyage ? (O/N)");
//			if (s2.equals("O")) {Site.getInstance().paiement();}
//			else {recherche();}
//		}
//	}

	public static Ville saisieVille(String moment) {
		Ville v =null;
		DAOVilleJDBC daoV = new DAOVilleJDBC();
		String v1Str = saisieString("\nSaisir votre ville "+moment);
		v = daoV.selectByNom(v1Str);
		return v;
	}



}
