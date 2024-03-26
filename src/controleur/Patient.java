package controleur;

public class Patient {
    private  int idpatient, idmedecin;
    private String nom, prenom, adresse, ville, telephone, email, dateNaissance, codePostal, sexe;

    public Patient(int idpatient, String nom, String prenom, String adresse, String ville, String telephone, String email, String dateNaissance, String codePostal, String sexe, int idmedecin) {
        super();
        this.idpatient = idpatient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.codePostal = codePostal;
        this.sexe = sexe;
        this.idmedecin = idmedecin;
    }

    public Patient(String nom, String prenom, String adresse, String ville, String telephone, String email, String dateNaissance, String codePostal, String sexe, int idmedecin) {
        super();
        this.idpatient = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.codePostal = codePostal;
        this.sexe = sexe;
        this.idmedecin = idmedecin;
    }

    public int getIdpatient() {
        return idpatient;
    }

    public void setIdpatient(int idpatient) {
        this.idpatient = idpatient;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getIdMedecin() {
        return idmedecin;
    }

    public void setIdMedecin(int idmedecin) {
        this.idmedecin = idmedecin;
    }
}