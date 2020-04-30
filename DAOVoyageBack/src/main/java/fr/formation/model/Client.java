package fr.formation.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Client")
public class Client extends Utilisateur {
	
	@OneToMany(mappedBy = "destinataire")
	List<Cagnotte> cagnottesDestinataire = new ArrayList<>();
	
	@OneToMany(mappedBy = "initiateur")
	List<Cagnotte> cagnottesInitiateur = new ArrayList<>();
	
	@ManyToMany(mappedBy = "participants")
	List<Cagnotte> cagnottesParticipant = new ArrayList<>();
	

	@OneToMany(mappedBy = "client")
	private List<Voyage> voyages;
	
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	
	public Client() {
		
	}
	
	public Client (String login, String password, String adresseMail) {
		this.login = login;
		this.password = password;
		this.adresseMail = adresseMail;
		this.typeCompte = "Client";
	}
	
	public Client (int id, String login, String password, String adresseMail) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.adresseMail = adresseMail;
		this.typeCompte = "Client";
	}
	
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	
	
	public List<Voyage> getVoyages() {
		return voyages;
	}
	public List<Cagnotte> getCagnottesDestinataire() {
		return cagnottesDestinataire;
	}

	public void setVoyages(List<Voyage> voyages) {
		this.voyages = voyages;
	}

	public void setCagnottesDestinataire(List<Cagnotte> cagnottesDestinataire) {
		this.cagnottesDestinataire = cagnottesDestinataire;
	}

	public List<Cagnotte> getCagnottesInitiateur() {
		return cagnottesInitiateur;
	}

	public void setCagnottesInitiateur(List<Cagnotte> cagnottesInitiateur) {
		this.cagnottesInitiateur = cagnottesInitiateur;
	}

	public List<Cagnotte> getCagnottesParticipant() {
		return cagnottesParticipant;
	}

	public void setCagnottesParticipant(List<Cagnotte> cagnottesParticipant) {
		this.cagnottesParticipant = cagnottesParticipant;
	}
	
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//

	public void creationCagnotte(Voyage v) {
		Cagnotte c = new Cagnotte(v.getPrix(), v.getClient(), this, v);
		this.daoCagnotte.save(c);
	}
	
	public void participer(double somme, Cagnotte c) {
		double n = c.getSommeAPayer()-somme;
		c.setSommeAPayer(n);
		c.addParticipant(this);
		this.daoCagnotte.save(c);
	}
	
	public void archives() {
		cagnottesDestinataire = this.daoCagnotte.findByDestinataire(this);
		cagnottesInitiateur = this.daoCagnotte.findByInitiateur(this);
		cagnottesParticipant = this.daoCagnotte.findByParticipant(this);
		noDoublon(cagnottesDestinataire);
		noDoublon(cagnottesInitiateur);
		noDoublon(cagnottesParticipant);
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
	
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//

	@Override
	public String toString() {
		return "Client [id=" + id + ", login=" + login + ", password=" + password + ", adresseMail=" + adresseMail
				+ ", typeCompte=" + typeCompte + "]";
	}



}
