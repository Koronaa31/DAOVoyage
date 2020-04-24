package model;

import java.util.ArrayList;
import java.util.List;

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

	public void creationCagnotte(Client destinataire, Voyage v) {
		Cagnotte c = new Cagnotte(v.getPrix(), destinataire, this, v);
		Site.getInstance().getDaoVoyage().insert(v);
		Site.getInstance().getDaoCagnotte().insert(c);
	}
	
	public void participer(double somme, Cagnotte c) {
		double n = c.getSommeAPayer()-somme;
		if(n >= 0) {
			c.setSommeAPayer(n);
			c.addParticipant(this);
			Site.getInstance().getDaoCagnotte().update(c);
		} 
	}
	
	public void archives() {
		cagnottesDestinataire = Site.getInstance().getDaoCagnotte().selectByDestinataire(this);
		cagnottesInitiateur = Site.getInstance().getDaoCagnotte().selectByInitiateur(this);
		cagnottesParticipant = Site.getInstance().getDaoCagnotte().selectByParticipant(this);
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
