package model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Client")
public class Client extends Utilisateur {

	@OneToMany(mappedBy = "client")
	private List<Voyage> voyages;
	
	
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
	
	public List<Voyage> getVoyages() {
		return voyages;
	}

	public void setVoyages(List<Voyage> voyages) {
		this.voyages = voyages;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", login=" + login + ", password=" + password + ", adresseMail=" + adresseMail
				+ ", typeCompte=" + typeCompte + "]";
	}



}
