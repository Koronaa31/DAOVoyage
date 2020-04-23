package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Client")
public class Client extends Utilisateur {

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

	@Override
	public String toString() {
		return "Client [id=" + id + ", login=" + login + ", password=" + password + ", adresseMail=" + adresseMail
				+ ", typeCompte=" + typeCompte + "]";
	}



}
