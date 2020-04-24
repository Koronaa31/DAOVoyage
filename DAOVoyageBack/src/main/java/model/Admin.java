package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Utilisateur {

	public Admin() {

	}

	public Admin (int id, String login, String password, String adresseMail) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.adresseMail = adresseMail;
		this.typeCompte = "Admin";
	}
	
}
