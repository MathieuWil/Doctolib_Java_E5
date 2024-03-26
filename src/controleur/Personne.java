package controleur;

public class Personne {
    private int idpersonne;
    private String nom, prenom, email, mdp, tel, role;

    public Personne(int idpersonne, String nom, String prenom, String email, String mdp, String tel, String role) {
        super();
        this.idpersonne = idpersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.role = role;
    }

    public Personne(String nom, String prenom, String email, String mdp, String tel, String role) {
        super();
        this.idpersonne = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.role = role;
    }

    public Personne(int idpersonne, String nom, String prenom, String email, String mdp, String tel) {
        super();
        this.idpersonne = idpersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
    }

    public Personne(String nom, String prenom, String email, String mdp, String tel) {
        super();
        this.idpersonne = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
    }

    public int getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(int idpersonne) {
        this.idpersonne = idpersonne;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
