package controleur;

public class RendezVous {
    int idrendezvous;
    String daterdv, heure, etat; 
    private int idpatient, idmedecin;

    public RendezVous(int idrendezvous, String daterdv, String heure, String etat, int idpatient, int idmedecin) {
        super();
        this.idrendezvous = idrendezvous;
        this.daterdv = daterdv;
        this.heure = heure;
        this.etat = etat;
        this.idpatient = idpatient;
        this.idmedecin = idmedecin;
    }

    public RendezVous(String daterdv, String heure, String etat, int idpatient, int idmedecin) {
        super();
        this.idrendezvous = 0;
        this.daterdv = daterdv;
        this.heure = heure;
        this.etat = etat;
        this.idpatient = idpatient;
        this.idmedecin = idmedecin;
    }

    public int getIdrendezvous() {
        return idrendezvous;
    }

    public void setIdrendezvous(int idrendezvous) {
        this.idrendezvous = idrendezvous;
    }

    public String getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(String daterdv) {
        this.daterdv = daterdv;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(int idpatient) {
        this.idpatient = idpatient;
    }

    public int getIdmedecin() {
        return idmedecin;
    }

    public void setIdmedecin(int idmedecin) {
        this.idmedecin = idmedecin;
    }
}
