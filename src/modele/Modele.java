package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Medecin;
import controleur.Patient;
import controleur.Personne;
import controleur.Prescription;
import controleur.RendezVous;

public class Modele {

    private static Bdd uneBdd = new Bdd ("localhost:3307","rdv_medecin","root","");

    /******************* Patients *******************/
    public static void ajouterPatient(String nom, String prenom, String adresse, String ville, String telephone, String email, String dateNaissance, String codePostal, String sexe, int idmedecin) {
        String requete = "insert into patient values (null, '" + nom + "', '" + prenom + "', '" + adresse + "', '" + ville + "', '" + telephone + "', '" + email + "', '" + dateNaissance + "', '" + codePostal + "', '" + sexe + "', '" + idmedecin + "')";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void ajouterPatient(Patient unPatient) {
        String requete = "insert into patient values (null, '" + unPatient.getNom() + "', '" + unPatient.getPrenom() + "', '" + unPatient.getAdresse() + "', '" + unPatient.getVille() + "', '" + unPatient.getTelephone() + "', '" + unPatient.getEmail() + "', '" + unPatient.getDateNaissance() + "', '" + unPatient.getCodePostal() + "', '" + unPatient.getSexe() + "', '" + unPatient.getIdMedecin() + "')";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static Patient selectWherePatient(String nom, String prenom, String adresse, String ville, String telephone,
            String email, String dateNaissance, String codePostal, String sexe) {
        Patient unPatient = null;
        String requete = "select * from patient where nom = '" + nom + "' and prenom = '" + prenom + "' and adresse = '" + adresse + "' and ville = '" + ville + "' and telephone = '" + telephone + "' and email = '" + email + "' and dateNaissance = '" + dateNaissance + "' and codePostal = '" + codePostal + "' and sexe = '" + sexe + "'";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            if (unRes.next()) {
                unPatient = new Patient(
                    unRes.getInt("idpatient"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("adresse"),
                    unRes.getString("ville"),
                    unRes.getString("telephone"),
                    unRes.getString("email"),
                    unRes.getString("dateNaissance"),
                    unRes.getString("codePostal"),
                    unRes.getString("sexe"),
                    unRes.getInt("idmedecin")
                );
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        
        return unPatient;
    }

    public static void updatePatient(Patient unPatient) {
        String requete = "update patient set nom = '" + unPatient.getNom() + "', prenom = '" + unPatient.getPrenom() + "', adresse = '" + unPatient.getAdresse() + "', ville = '" + unPatient.getVille() + "', telephone = '" + unPatient.getTelephone() + "', email = '" + unPatient.getEmail() + "', dateNaissance = '" + unPatient.getDateNaissance() + "', codePostal = '" + unPatient.getCodePostal() + "', sexe = '" + unPatient.getSexe() + "', idmedecin = '" + unPatient.getIdMedecin() + "' where idpatient = " + unPatient.getIdpatient();
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void deletePatient(int idpatient) {
        String requete = "delete from patient where idpatient = " + idpatient;
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static String[] selectAllPatients() {
        String requete = "select * from patient";
        String tab[] = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            int nbLignes = 0;
            if (unRes.last()) {
                nbLignes = unRes.getRow();
                unRes.beforeFirst();
            }
            tab = new String[nbLignes];
            int i = 0;
            while (unRes.next()) {
                tab[i] = unRes.getString("idpatient") + " " + unRes.getString("nom") + " " + unRes.getString("prenom");
                i++;
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return tab;
    }

    public static ArrayList<Patient> selectAllPatients(String filtre) {
        ArrayList<Patient> lesPatients = new ArrayList<Patient>();
        String requete = ""; 
        if (filtre.equals("")){
            requete = "select * from patient ;";
        }
        else{
            requete = "select * from patient where nom like '%" + filtre + "%' or prenom like '%" + filtre + "%' or adresse like '%" + filtre + "%' or ville like '%" + filtre + "%' or telephone like '%" + filtre + "%' or email like '%" + filtre + "%' or dateNaissance like '%" + filtre + "%' or codePostal like '%" + filtre + "%' or sexe like '%" + filtre + "%'";
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            while (unRes.next()) {
                Patient unPatient = new Patient(
                    unRes.getInt("idpatient"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("adresse"),
                    unRes.getString("ville"),
                    unRes.getString("telephone"),
                    unRes.getString("email"),
                    unRes.getString("dateNaissance"),
                    unRes.getString("codePostal"),
                    unRes.getString("sexe"),
                    unRes.getInt("idmedecin")
                );
                lesPatients.add(unPatient);
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesPatients;
    }


    /******************* Medecins *******************/
    public static void updateMedecin(Medecin unMedecin) {
        String requete = "update medecin set nom = '" + unMedecin.getNom() + "', prenom = '" + unMedecin.getPrenom() + "', tel = '" + unMedecin.getTel() + "', email = '" + unMedecin.getMail() + "', specialite = '" + unMedecin.getSpecialite() + "' where idmedecin = " + unMedecin.getIdmedecin();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void deleteMedecin(int idmedecin) {
        String requete = "delete from medecin where idmedecin = " + idmedecin;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void insertMedecin(String nom, String prenom, String tel, String email, String specialite) {
        String requete = "insert into medecin values (null, '" + nom + "', '" + prenom + "', '" + email + "', '" + tel + "', '" + specialite + "')";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static ArrayList<Medecin> selectAllMedecins(String filtre) {
        ArrayList<Medecin> lesMedecins = new ArrayList<Medecin>();
        String requete = ""; 
        if (filtre.equals("")){
            requete = "select * from medecin ;";
        }
        else{
            requete = "select * from medecin where nom like '%" + filtre + "%' or prenom like '%" + filtre + "%' or tel like '%" + filtre + "%' or email like '%" + filtre + "%' or specialite like '%" + filtre + "%'";
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            while (unRes.next()) {
                Medecin unMedecin = new Medecin(
                    unRes.getInt("idmedecin"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("tel"),
                    unRes.getString("email"),
                    unRes.getString("specialite")
                );
                lesMedecins.add(unMedecin);
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesMedecins;
    }

    public static Medecin selectWhereMedecin(String nom, String prenom, String email, String tel, String specialite) {
        Medecin unMedecin = null;
        String requete = "select * from medecin where nom = '" + nom + "' and prenom = '" + prenom + "' and email = '" + email + "' and tel = '" + tel + "' and specialite = '" + specialite + "'";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            if (unRes.next()) {
                unMedecin = new Medecin(
                    unRes.getInt("idmedecin"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("tel"),
                    unRes.getString("email"),
                    unRes.getString("specialite")
                );
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unMedecin;
    }
    
    public static void insertMedecin(Medecin unMedecin) {
        String requete = "insert into medecin values (null, '" + unMedecin.getNom() + "', '" + unMedecin.getPrenom() + "', '" + unMedecin.getMail() + "', '" + unMedecin.getTel() + "', '" + unMedecin.getSpecialite() + "')";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static String[] selectAllMedecins() {
        String requete = "select * from medecin";
        String tab[] = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            int nbLignes = 0;
            if (unRes.last()) {
                nbLignes = unRes.getRow();
                unRes.beforeFirst();
            }
            tab = new String[nbLignes];
            int i = 0;
            while (unRes.next()) {
                tab[i] = unRes.getInt("idmedecin") +" " + unRes.getString("nom") + " " + unRes.getString("prenom");
                i++;
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return tab;
    }

    /******************* Connections *******************/
    public static Personne verifConnexion(String login, String mdp) {
        Personne unePersonne = null;
        String requete = "select * from personne where email = '" + login + "' and mdp = '" + mdp + "'";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            if (unRes.next()) {
                unePersonne = new Personne(  
                    unRes.getInt("idpersonne"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("email"),
                    unRes.getString("mdp"),
                    unRes.getString("tel"),
                    unRes.getString("role")
                );
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unePersonne;
    }

    /******************* Personne *******************/
    public static void ajouterPersonne(String nom, String prenom, String email, String tel, String role, String mdp) {
        String requete = "insert into personne values (null, '" + nom + "', '" + prenom + "', '" + email + "', '" + mdp + "', '" + tel + "', '" + role + "')";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void ajouterPersonne(Personne unePersonne) {
        String requete = "insert into personne values (null, '" + unePersonne.getNom() + "', '" + unePersonne.getPrenom() + "', '" + unePersonne.getEmail() + "', '" + unePersonne.getMdp() + "', '" + unePersonne.getTel() + "', '" + unePersonne.getRole() + "')";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void updatePersonneProfil(Personne unePersonne) {
        String requete = "update personne set nom = '" + unePersonne.getNom() + "', prenom = '" + unePersonne.getPrenom() + "', email = '" + unePersonne.getEmail() + "', mdp = '" + unePersonne.getMdp() + "', tel = '" + unePersonne.getTel() + "', role = '" + unePersonne.getRole() + "' where idpersonne = " + unePersonne.getIdpersonne();
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static Personne selectWherePersonne(String nom, String prenom, String email, String tel) {
        Personne unePersonne = null;
        String requete = "select * from personne where nom = '" + nom + "' and prenom = '" + prenom + "' and email = '" + email + "' and tel = '" + tel + "'";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            if (unRes.next()) {
                unePersonne = new Personne(
                    unRes.getInt("idpersonne"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("email"),
                    unRes.getString("mdp"),
                    unRes.getString("tel"),
                    unRes.getString("role")
                );
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unePersonne;
    }

    public static Personne selectWherePersonne(String nom, String prenom, String email, String tel, String role) {
        Personne unePersonne = null;
        String requete = "select * from personne where nom = '" + nom + "' and prenom = '" + prenom + "' and email = '" + email + "' and tel = '" + tel + "' and role = '" + role + "'";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            if (unRes.next()) {
                unePersonne = new Personne(
                    unRes.getInt("idpersonne"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("email"),
                    unRes.getString("mdp"),
                    unRes.getString("tel"),
                    unRes.getString("role")
                );
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unePersonne;
    }

    public static void deletePersonne(int idpersonne) {
        String requete = "delete from personne where idpersonne = " + idpersonne;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }
    
    public static void updatePersonne(Personne unePersonne) {
        String requete = "update personne set nom = '" + unePersonne.getNom() + "', prenom = '" + unePersonne.getPrenom() + "', email = '" + unePersonne.getEmail() + "', mdp = '" + unePersonne.getMdp() + "', tel = '" + unePersonne.getTel() + "', role = '" + unePersonne.getRole() + "' where idpersonne = " + unePersonne.getIdpersonne();
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static String[] selectAllPersonne() {
        String requete = "select * from personne";
        String tab[] = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            int nbLignes = 0;
            if (unRes.last()) {
                nbLignes = unRes.getRow();
                unRes.beforeFirst();
            }
            tab = new String[nbLignes];
            int i = 0;
            while (unRes.next()) {
                tab[i] = unRes.getString("idpersonne") + " " + unRes.getString("nom") + " " + unRes.getString("prenom") + " " + unRes.getString("email") + " " + unRes.getString("mdp") + " " + unRes.getString("tel");
                i++;
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return tab;
    }

    public static ArrayList<Personne> selectAllPersonne(String filtre) {
        ArrayList<Personne> lesPersonnes = new ArrayList<Personne>();
        String requete = ""; 
        if (filtre.equals("")){
            requete = "select * from personne ;";
        }
        else{
            requete = "select * from personne where nom like '%" + filtre + "%' or prenom like '%" + filtre + "%' or email like '%" + filtre + "%' or mdp like '%" + filtre + "%' or tel like '%" + filtre + "%'";
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            while (unRes.next()) {
                Personne unePersonne = new Personne(
                    unRes.getInt("idpersonne"),
                    unRes.getString("nom"),
                    unRes.getString("prenom"),
                    unRes.getString("email"),
                    unRes.getString("mdp"),
                    unRes.getString("tel"),
                    unRes.getString("role")
                );
                lesPersonnes.add(unePersonne);
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesPersonnes;
    }

    /******************* Rendez-vous *******************/

    public static void ajouterRdv(String date, String heure, String etatRdv, int patientRdv, int medecinRdv) {
        String requete = "insert into rendezvous values (null, '" + date + "', '" + heure + "', '" + etatRdv + "', '" + patientRdv + "', '" + medecinRdv + "')";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static void ajouterRdv(RendezVous unRendezVous) {
        String requete = "insert into rendezvous values (null, '" + unRendezVous.getDaterdv() + "', '" + unRendezVous.getHeure() + "', '" + unRendezVous.getEtat() + "', '" + unRendezVous.getIdpatient() + "', '" + unRendezVous.getIdmedecin() + "')";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static RendezVous selectWhereRdv(String date, String heure, String etatRdv, int patientRdv, int medecinRdv) {
        RendezVous unRdv = null;
        String requete = "select * from rendezvous where daterdv = '" + date + "' and heure = '" + heure + "' and etat = '" + etatRdv + "' and idpatient = '" + patientRdv + "' and idmedecin = '" + medecinRdv + "'";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            if (unRes.next()) {
                unRdv = new RendezVous(
                    unRes.getInt("idrendezvous"),
                    unRes.getString("daterdv"),
                    unRes.getString("heure"),
                    unRes.getString("etat"),
                    unRes.getInt("idpatient"),
                    unRes.getInt("idmedecin")
                );
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return unRdv;
    }

    public static void updateRdv(RendezVous unRendezVous) {
        String requete = "update rendezvous set daterdv = '" + unRendezVous.getDaterdv() + "', heure = '" + unRendezVous.getHeure() + "', etat = '" + unRendezVous.getEtat() + "', idpatient = '" + unRendezVous.getIdpatient() + "', idmedecin = '" + unRendezVous.getIdmedecin() + "' where idrendezvous = " + unRendezVous.getIdrendezvous();
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static String[] selectAllRdv() {
        String requete = "select * from rendezvous";
        String tab[] = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            int nbLignes = 0;
            if (unRes.last()) {
                nbLignes = unRes.getRow();
                unRes.beforeFirst();
            }
            tab = new String[nbLignes];
            int i = 0;
            while (unRes.next()) {
                tab[i] = unRes.getString("idrendezvous") + " " + unRes.getString("daterdv") + " " + unRes.getString("heure") + " " + unRes.getString("etat") + " " + unRes.getInt("idpatient") + " " + unRes.getInt("idmedecin");
                i++;
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return tab;
    }

    public static ArrayList<RendezVous> selectAllRDV(String filtre) {
        ArrayList<RendezVous> lesRdv = new ArrayList<RendezVous>();
        String requete = ""; 
        if (filtre.equals("")){
            requete = "select * from rendezvous ;";
        }
        else{
            requete = "select * from rendezvous where daterdv like '%" + filtre + "%' or heure like '%" + filtre + "%' or etat like '%" + filtre + "%' or idpatient like '%" + filtre + "%' or idmedecin like '%" + filtre + "%'";
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            while (unRes.next()) {
                RendezVous unRdv = new RendezVous(
                    unRes.getInt("idrendezvous"),
                    unRes.getString("daterdv"),
                    unRes.getString("heure"),
                    unRes.getString("etat"),
                    unRes.getInt("idpatient"),
                    unRes.getInt("idmedecin")
                );
                lesRdv.add(unRdv);
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesRdv;
    }

    public static void deleteRdv(int idrendezvous) {
        String requete = "delete from rendezvous where idrendezvous = " + idrendezvous;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    /******************* Prescription *******************/
    public static String[] selectAllPrescription() {
        String requete = "select * from prescription";
        String tab[] = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            int nbLignes = 0;
            if (unRes.last()) {
                nbLignes = unRes.getRow();
                unRes.beforeFirst();
            }
            tab = new String[nbLignes];
            int i = 0;
            while (unRes.next()) {
                tab[i] = unRes.getString("idprescription") + " " + unRes.getString("date") + " " + unRes.getString("medicament") + " " + unRes.getString("patient") + " " + unRes.getString("medecin");
                i++;
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return tab;
    }

    public static void deletePrescription(int idprescription) {
        String requete = "delete from prescription where idprescription = " + idprescription;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static ArrayList<Prescription> selectAllPrescriptions(String filtre) {
        ArrayList<Prescription> lesPrescriptions = new ArrayList<Prescription>();
        String requete = ""; 
        if (filtre.equals("")){
            requete = "select * from prescription ;";
        }
        else{
            requete = "select * from prescription where idprescription like '%" + filtre + "%' or dateprescription like '%" + filtre + "%' or medicament like '%" + filtre + "%' or idpatient like '%" + filtre + "%' or idmedecin like '%" + filtre + "%'";
        }

        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            while (unRes.next()) {
                Prescription unePrescription = new Prescription(
                    unRes.getInt("idprescription"),
                    unRes.getString("dateprescription"),
                    unRes.getString("medicament"),
                    unRes.getInt("idpatient"),
                    unRes.getInt("idmedecin")
                );
                lesPrescriptions.add(unePrescription);
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesPrescriptions;
    }

    public static void ajouterPrescription(Prescription unePrescription) {
        String requete = "insert into prescription values (null, '" + unePrescription.getDateprescription() + "', '" + unePrescription.getMedicament() + "', '" + unePrescription.getIdpatient() + "', '" + unePrescription.getIdmedecin() + "')";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    public static Prescription selectWherePrescription(String dateprescription, String medicament, int idpatient, int idmedecin) {
        Prescription unePrescription = null;
        String requete = "select * from prescription where dateprescription = '" + dateprescription + "' and medicament = '" + medicament + "' and idpatient = '" + idpatient + "' and idmedecin = '" + idmedecin + "'";
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            if (unRes.next()) {
                unePrescription = new Prescription(
                    unRes.getInt("idprescription"),
                    unRes.getString("dateprescription"),
                    unRes.getString("medicament"),
                    unRes.getInt("idpatient"),
                    unRes.getInt("idmedecin")
                );
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        
        return unePrescription;
    }

    public static void updatePrescription(Prescription unePrescription) {
        String requete = "update prescription set dateprescription = '" + unePrescription.getDateprescription() + "', medicament = '" + unePrescription.getMedicament() + "', idpatient = '" + unePrescription.getIdpatient() + "', idmedecin = '" + unePrescription.getIdmedecin() + "' where idprescription = " + unePrescription.getIdprescription();
        System.out.println(requete);
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
    }

    /******************* Specialités *******************/
    public static ArrayList<String> selectAllSpecialites() {
        String requete = "select * from specialite";
        ArrayList<String> lesSpecialites = new ArrayList<String>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            while (unRes.next()) {
                lesSpecialites.add(unRes.getString("libelle"));
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesSpecialites;
    }

    /******************* Profession *******************/
    public static ArrayList<String> selectAllProfessions() {
        String requete = "select * from professions";
        ArrayList<String> lesProfessions = new ArrayList<String>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unRes = unStat.executeQuery(requete);
            while (unRes.next()) {
                lesProfessions.add(unRes.getString("libelle"));
            }
            unRes.close();
            unStat.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'execution de la requete : " + requete);
        }
        return lesProfessions;
    }
}