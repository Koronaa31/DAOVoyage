package model;

public enum Transport {

    Avion(0.147,920),Train(0.176,400),Bus(0.048,80),Cheval(0.07,30),PoussePousse(0.02,5);

    private double prixAuKm;
    private double vitesse; //en km/h

    private Transport(double prixAuKm, double vitesse) {
        this.prixAuKm=prixAuKm;
        this.vitesse=vitesse;
    }

    public double getPrixAuKm() {
        return prixAuKm;
    }

    public double getVitesse() {
        return vitesse;
    }

}
