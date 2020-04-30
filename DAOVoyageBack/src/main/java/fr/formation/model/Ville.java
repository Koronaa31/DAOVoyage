package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ville")
public class Ville {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "nom")
    private String nom;
    
	@Column(name = "longitude")    
    private double longitude;
    
	@Column(name = "latitude")
    private double latitude;

    public Ville(int id, String nom, double longitude, double latitude) {
        this.id = id;
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public Ville(String nom, double longitude, double latitude) {
        this.id = id;
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Ville() {
    	
    }
    
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
    
    

}
