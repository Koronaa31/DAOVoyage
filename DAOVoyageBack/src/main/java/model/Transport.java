package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transport")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;
    
    @Column(name="nom")
    private String nom;
    
    @Column(name="prix_au_km")
    private double prixAuKm;
    
    @Column(name="vitesse")
    private double vitesse; //en km/h

    
    //-------------------------------------------------//
    //----------------Constructeurs--------------------//
    //-------------------------------------------------//
    
    public Transport() {
    	
    }
    
    public Transport(String nom, double prixAuKm, double vitesse) {
    	this.nom=nom;
        this.prixAuKm=prixAuKm;
        this.vitesse=vitesse;
    }

    //-------------------------------------------------//
    //----------------getters and setters--------------//
    //-------------------------------------------------//
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrixAuKm() {
		return prixAuKm;
	}

	public void setPrixAuKm(double prixAuKm) {
		this.prixAuKm = prixAuKm;
	}

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	//-------------------------------------------------//
    //----------------toString-------------------------//
    //-------------------------------------------------//
	
	@Override
	public String toString() {
		return "Transport [id=" + id + ", nom=" + nom + ", prixAuKm=" + prixAuKm + ", vitesse=" + vitesse + "]";
	}

    
    
    
}
