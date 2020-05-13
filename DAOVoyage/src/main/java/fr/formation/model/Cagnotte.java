package fr.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cagnotte")
public class Cagnotte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "somme_totale")
	private double sommeTotale;
	
	@Column(name = "somme_a_payer")
	private double sommeAPayer;
	
	@ManyToOne
	@JoinColumn(name = "id_destinataire")
	private Client destinataire;
	
	@ManyToOne
	@JoinColumn(name = "id_initiateur")
	private Client initiateur;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "cagnotte_client",
			joinColumns = @JoinColumn(name = "id_cagnotte", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_participant", referencedColumnName = "id")
			)
	private List<Client> participants = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_voyage")
	private Voyage voyage;
	
	
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	
	public Cagnotte() {
	}

	public Cagnotte(double sommeTotale, Client destinataire, Client initiateur, Voyage voyage) {
		this.sommeTotale = sommeTotale;
		this.sommeAPayer = sommeTotale;
		this.destinataire = destinataire;
		this.initiateur = initiateur;
		this.voyage = voyage;
	}
	
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSommeTotale() {
		return sommeTotale;
	}

	public void setSommeTotale(double sommeTotale) {
		this.sommeTotale = sommeTotale;
	}

	public double getSommeAPayer() {
		return sommeAPayer;
	}

	public void setSommeAPayer(double sommeAPayer) {
		this.sommeAPayer = sommeAPayer;
	}

	public Client getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Client destinataire) {
		this.destinataire = destinataire;
	}

	public Client getInitiateur() {
		return initiateur;
	}

	public void setInitiateur(Client initiateur) {
		this.initiateur = initiateur;
	}

	public List<Client> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Client> participants) {
		this.participants = participants;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	
	public void addParticipant(Client p) {
		this.participants.add(p);
		p.getCagnottesParticipant().add(this);
	}
	
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	//------------------------------------------------------------//
	
	@Override
	public String toString() {
		return "Cagnotte [sommeTotale=" + sommeTotale + ", sommeAPayer=" + sommeAPayer + ", destinataire="
				+ destinataire + ", initiateur=" + initiateur + ", participants=" + participants + ", voyage=" + voyage
				+ "]";
	}
	
	
	
	
}
