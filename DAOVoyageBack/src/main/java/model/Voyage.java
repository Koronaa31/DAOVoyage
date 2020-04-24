package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "voyage")
public class Voyage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_ville_depart")
    private Ville v1;
	
    @ManyToOne
    @JoinColumn(name = "id_ville_arrivee")
    private Ville v2;
    
    @ManyToOne
    @JoinColumn(name = "transport")
    private Transport t;
    
    @Column(name = "prix", nullable = false)
    private double prix;
    
    @Column(name = "duree", length = 50, nullable = false)
    private String duree;
    
    @Column(name = "statut", length = 50, nullable = false)
    private String statut;
    
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    	
    
    public Voyage(Ville v1, Ville v2, Transport t, Client client) {
		this.v1 = v1;
		this.v2 = v2;
		this.t = t;
		this.client = client;
        prix = calculPrix(v1,v2,t);
        duree = calculDureeHMin(v1,v2,t);
	}

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
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

    public String getStatut() {
		return statut;
	}
    
	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
        //  Distance = 128sqrt((x2-x1)²+(y2-y1)²)
    	return 128*Math.sqrt(Math.pow(v1.getLongitude()-v2.getLongitude(),2) + Math.pow(v1.getLatitude()-v2.getLatitude(),2));
    }

	@Override
	public String toString() {
		return "Voyage [id=" + id + ", v1=" + v1 + ", v2=" + v2 + ", t=" + t + ", prix=" + prix + ", duree=" + duree
				+ ", statut=" + statut + ", client=" + client + "]";
	}


   

}
