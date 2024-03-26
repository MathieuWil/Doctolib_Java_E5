package controleur;

public class Medecin {
    int idmedecin;
    String nom, prenom, tel, email, specialite;

    public Medecin(int idmedecin, String nom, String prenom, String tel, String email, String specialite) {
        super();
        this.idmedecin = idmedecin;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
    }

    public Medecin(String nom, String prenom, String tel, String email, String specialite) {
        super();
        this.idmedecin = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
    }

    public int getIdmedecin() {
        return idmedecin;
    }

    public void setIdmedecin(int idmedecin) {
        this.idmedecin = idmedecin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
