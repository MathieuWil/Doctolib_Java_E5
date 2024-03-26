package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {

    /******************* Patients *******************/
    public static void ajouterPatient(String nom, String prenom, String adresse, String ville, String telephone, String email, String dateNaissance, String codePostal, String sexe, int idmedecin) {
        Modele.ajouterPatient(nom, prenom, adresse, ville, telephone, email, dateNaissance, codePostal, sexe, idmedecin);
    }

    public static Patient selectWherePatient(String nom, String prenom, String adresse, String ville, String telephone,
            String email, String dateNaissance, String codePostal, String sexe) {
        return Modele.selectWherePatient(nom, prenom, adresse, ville, telephone, email, dateNaissance, codePostal, sexe);
    }

    public static void updatePatient(Patient unPatient) {
        Modele.updatePatient(unPatient);
    }

    public static void deletePatient(int idpatient) {
        Modele.deletePatient(idpatient);
    }

    public static String[] selectAllPatients() {
        return Modele.selectAllPatients();
    }

    public static ArrayList<Patient> selectAllPatients(String filtre) {
        return Modele.selectAllPatients(filtre);
    }

    public static void ajouterPatient(Patient unPatient) {
        Modele.ajouterPatient(unPatient);
    }

    /******************* Medecins *******************/
    public static void updateMedecin(Medecin unMedecin) {
        Modele.updateMedecin(unMedecin);
    }

    public static void deleteMedecin(int idmedecin) {
        Modele.deleteMedecin(idmedecin);
    }

    public static void insertMedecin(Medecin unMedecin) {
        Modele.insertMedecin(unMedecin);
    }

    public static void insertMedecin(String nom, String prenom, String tel, String email, String specialite) {
        Modele.insertMedecin(nom, prenom, tel, email, specialite);
    }

    public static String[] selectAllMedecins() {
        return Modele.selectAllMedecins();
    }

    public static ArrayList<Medecin> selectAllMedecins(String filtre) {
        return Modele.selectAllMedecins(filtre);
    }

    public static Medecin selectWhereMedecin(String nom, String prenom, String email, String tel, String specialite) {
        return Modele.selectWhereMedecin(nom, prenom, email, tel, specialite);
    }

    /******************* Connections *******************/
    public static Personne verifConnexion(String login, String mdp) {
        return Modele.verifConnexion(login, mdp);
    }

    /******************* Personne *******************/
    public static void updatePersonne(Personne unePersonne) {
        Modele.updatePersonne(unePersonne);
    }

    public static String[] selectAllPersonne() {
        return Modele.selectAllPersonne();
    }

    public static ArrayList<Personne> selectAllPersonne(String filtre) {
        return Modele.selectAllPersonne(filtre);
    }

    public static void ajouterPersonne(String nom, String prenom, String email, String tel, String role, String mdp) {
        Modele.ajouterPersonne(nom, prenom, email, tel, role, mdp);
    }

    public static void ajouterPersonne(Personne unePersonne) {
        Modele.ajouterPersonne(unePersonne);
    }

    public static void updatePersonneProfil(Personne unePersonne) {
        Modele.updatePersonneProfil(unePersonne);
    }

    public static Personne selectWherePersonne(String nom, String prenom, String email, String tel, String role) {
        return Modele.selectWherePersonne(nom, prenom, email, tel, role);
    }

    public static Personne selectWherePersonne(String nom, String prenom, String email, String tel) {
        return Modele.selectWherePersonne(nom, prenom, email, tel);
    }

    public static void deletePersonne(int idpersonne) {
        Modele.deletePersonne(idpersonne);
    }

    /******************* Rendez-vous *******************/
    public static void ajouterRdv(String date, String heure, String etatRdv, int patientRdv, int medecinRdv) {
        Modele.ajouterRdv(date, heure, etatRdv, patientRdv, medecinRdv);
    }

    public static void ajouterRdv(RendezVous unRendezVous) {
        Modele.ajouterRdv(unRendezVous);
    }

    public static String[] selectAllRdv() {
        return Modele.selectAllRdv();
    }

    public static ArrayList<RendezVous> selectAllRDV(String filtre) {
        return Modele.selectAllRDV(filtre);
    }

    public static void deleteRdv(int idrendezvous) {
        Modele.deleteRdv(idrendezvous);
    }

    public static RendezVous selectWhereRdv(String date, String heure, String etatRdv, int patientRdv, int medecinRdv) {
        return Modele.selectWhereRdv(date, heure, etatRdv, patientRdv, medecinRdv);
    }

    public static void updateRdv(RendezVous unRendezVous) {
        Modele.updateRdv(unRendezVous);
    }

    /******************* Prescription *******************/
    public static String[] selectAllPrescription() {
        return Modele.selectAllPrescription();
    }

    public static void deletePrescription(int idprescription) {
        Modele.deletePrescription(idprescription);
    }

    public static ArrayList<Prescription> selectAllPrescriptions(String string) {
        return Modele.selectAllPrescriptions(string);
    }

    public static void ajouterPrescription(Prescription unePrescription) {
        Modele.ajouterPrescription(unePrescription);
    }

    public static Prescription selectWherePrescription(String dateprescription, String medicament, int idpatient, int idmedecin) {
        return Modele.selectWherePrescription(dateprescription, medicament, idpatient, idmedecin);
    }

    public static void updatePrescription(Prescription unePrescription) {
        Modele.updatePrescription(unePrescription);
    }

    /******************* Specialités *******************/
    public static ArrayList<String> selectAllSpecialites() {
        return Modele.selectAllSpecialites();
    }

    /******************* Professions *******************/
    public static ArrayList<String> selectAllProfessions() {
        return Modele.selectAllProfessions();
    }
}
