package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commandes")
public class Voyage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_commande")
	private int id;
	
	@Column(name = "ville1")
    private Ville v1;
    
    @Column(name = "ville2")
    private Ville v2;
    
    @Column(name = "transport")
    private Transport t;
    
    @Column
    private double prix;
    
    @Column
    private String duree;
    
    @ManyToOne
    @Column(name = "id_client")
    private Utilisateur utilisateur;
    
    
    public Voyage(Ville v1, Ville v2, Transport t, double prix, String duree) {
        this.v1 = v1;
        this.v2 = v2;
        this.t = t;
        this.prix = prix;
        this.duree = duree;
    }

    public Voyage(Ville v1, Ville v2, Transport t) {
        this.v1 = v1;
        this.v2 = v2;
        this.t = t;
        prix = calculPrix(v1,v2,t);
        duree = calculDureeHMin(v1,v2,t);
    }

    public Voyage() {
    	
    }
    
    public Ville getV1() {
        return v1;
    }

    public void setV1(Ville v1) {
        this.v1 = v1;
    }

    public Ville getV2() {
        return v2;
    }

    public void setV2(Ville v2) {
        this.v2 = v2;
    }

    public Transport getT() {
        return t;
    }

	public void setT(Transport t) {
        this.t = t;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

    private double calculDuree(Ville v1, Ville v2, Transport t) {
        return calculDistance(v1,v2)/t.getVitesse();
    }
    
    private String calculDureeHMin(Ville v1, Ville v2, Transport t) {
    	double dureeTot = calculDuree(v1,v2,t);
    	int h = (int)Math.floor(dureeTot);
    	int min = (int)(Math.floor((dureeTot-h)*60));
    	String duree=h+" h "+min+" min";
    	return duree;
    }

    private double calculPrix(Ville v1, Ville v2, Transport t) {
    	return Math.floor((calculDistance(v1,v2)*t.getPrixAuKm())*100)/100;
    }

    private double calculDistance(Ville v1,Ville v2) {
        //  Distance = 128sqrt((x2-x1)�+(y2-y1)�)
    	return 128*Math.sqrt(Math.pow(v1.getLongitude()-v2.getLongitude(),2) + Math.pow(v1.getLatitude()-v2.getLatitude(),2));
    }


    @Override
    public String toString() {
        return "Voyage [v1=" + v1 + ", v2=" + v2 + ", t=" + t + ", prix=" + prix + ", duree=" + duree + "]";
    }

}
