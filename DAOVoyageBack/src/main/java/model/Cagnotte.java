package model;

import java.util.ArrayList;
import java.util.List;

public class Cagnotte {
	
	private int somme;
	private Client destinataire;
	private Client initiateur;
	private List<Client> participants = new ArrayList<>();
	
	public Cagnotte() {
	}
	
	public Cagnotte(int somme, Client destinataire, Client initiateur, List<Client> participants) {
		this.somme = somme;
		this.destinataire = destinataire;
		this.initiateur = initiateur;
		this.participants = participants;
	}
	
	
}
