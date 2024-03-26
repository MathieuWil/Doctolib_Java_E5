DROP DATABASE IF EXISTS rdv_medecin;
CREATE DATABASE rdv_medecin;
USE rdv_medecin;

CREATE TABLE patient (
    idpatient INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    adresse VARCHAR(50) NOT NULL,
    ville VARCHAR(50) NOT NULL,
    telephone VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    dateNaissance DATE NOT NULL,
    codePostal VARCHAR(5) NOT NULL,
    sexe enum('Homme','Femme') NOT NULL,
    idmedecin INT(3) NOT NULL,
    PRIMARY KEY (idpatient),
    FOREIGN KEY (idmedecin) REFERENCES medecin(idmedecin)
);

CREATE TABLE medecin (
    idmedecin INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    tel VARCHAR(50) NOT NULL,
    specialite VARCHAR(50) NOT NULL,
    PRIMARY KEY (idmedecin)
);

CREATE TABLE rendezvous (
    idrendezvous INT(3) NOT NULL AUTO_INCREMENT,
    daterdv DATE NOT NULL,
    heure TIME NOT NULL,
    etat VARCHAR(50) NOT NULL,
    idpatient INT(3) NOT NULL,
    idmedecin INT(3) NOT NULL,
    PRIMARY KEY (idrendezvous),
    FOREIGN KEY (idpatient) REFERENCES patient(idpatient),
    FOREIGN KEY (idmedecin) REFERENCES medecin(idmedecin)
);

CREATE TABLE prescription (
    idprescription INT(3) NOT NULL AUTO_INCREMENT,
    dateprescription DATE NOT NULL,
    medicament VARCHAR(100) NOT NULL, 
    idpatient INT(3) NOT NULL,
    idmedecin INT(3) NOT NULL,
    PRIMARY KEY (idprescription),
    FOREIGN KEY (idpatient) REFERENCES patient(idpatient),
    FOREIGN KEY (idmedecin) REFERENCES medecin(idmedecin)
);

CREATE TABLE personne (
    idpersonne INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    mdp VARCHAR(50) NOT NULL,
    tel VARCHAR(50) NOT NULL,
    role ENUM ('Patient','Medecin','Admin') NOT NULL,
    PRIMARY KEY (idpersonne)
);

CREATE TABLE professions (
    idprofession INT(3) NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY (idprofession)
);

INSERT INTO professions VALUES (null, 'Cardiologue');
INSERT INTO professions VALUES (null, 'Dentiste');
INSERT INTO professions VALUES (null, 'Dermatologue');
INSERT INTO professions VALUES (null, 'Gynecologue');
INSERT INTO professions VALUES (null, 'Ophtalmologue');
INSERT INTO professions VALUES (null, 'Pediatre');
INSERT INTO professions VALUES (null, 'Psychiatre');
INSERT INTO professions VALUES (null, 'Radiologue');
INSERT INTO professions VALUES (null, 'Urologue');
INSERT INTO professions VALUES (null, 'Chirurgien');
INSERT INTO professions VALUES (null, 'Orthopediste');
INSERT INTO professions VALUES (null, 'Oncologue');
INSERT INTO professions VALUES (null, 'Neurologue');
INSERT INTO professions VALUES (null, 'Pneumologue');
INSERT INTO professions VALUES (null, 'Endocrinologue');
INSERT INTO professions VALUES (null, 'Rhumatologue');
INSERT INTO professions VALUES (null, 'Nephrologue');
INSERT INTO professions VALUES (null, 'Hematologue');
INSERT INTO professions VALUES (null, 'Allergologue');
INSERT INTO professions VALUES (null, 'Anesthesiste');
INSERT INTO professions VALUES (null, 'Chirurgien-dentiste');
INSERT INTO professions VALUES (null, 'Infirmier');
INSERT INTO professions VALUES (null, 'Sage-femme');
INSERT INTO professions VALUES (null, 'Kinesitherapeute');
INSERT INTO professions VALUES (null, 'Orthophoniste');
INSERT INTO professions VALUES (null, 'Psychologue');
INSERT INTO professions VALUES (null, 'Dieteticien');
INSERT INTO professions VALUES (null, 'Podologue');
INSERT INTO professions VALUES (null, 'Opticien');
INSERT INTO professions VALUES (null, 'Audioprothesiste');
INSERT INTO professions VALUES (null, 'Orthoptiste');
INSERT INTO professions VALUES (null, 'Prothesiste-dentaire');
INSERT INTO professions VALUES (null, 'Assistant-medical');
INSERT INTO professions VALUES (null, 'Assistant-social');
INSERT INTO professions VALUES (null, 'Ambulancier');
INSERT INTO professions VALUES (null, 'Pompier');
INSERT INTO professions VALUES (null, 'Pharmacien');
INSERT INTO professions VALUES (null, 'Psychomotricien');
INSERT INTO professions VALUES (null, 'Autre');

INSERT INTO personne VALUES (null, 'Walker', 'Maxence', 'mwalker@gmail.com', 'Superb@pt95!', '0615141213', 'Patient');
INSERT INTO personne VALUES (null, 'Wilkosz', 'Matthieu', 'mwilkosz@gmail.com', 'Superb@pt95!', '0615141213', 'Medecin');
INSERT INTO personne VALUES (null, 'Lucbert', 'Baptiste', 'blucbert@gmail.com', 'Superb@pt95!', '0615141213', 'Admin');
INSERT INTO personne VALUES (null, 'Youssoufa', 'Ilyes', 'iyoussoufa@gmail.com', 'Superb@pt95!', '0615141213', 'Admin');

INSERT INTO medecin VALUES (null, 'Dupont', 'Jean', 'jean.dupont@example.com', '0123456789', 'Cardiologue');
INSERT INTO medecin VALUES (null, 'Martin', 'Sophie', 'sophie.martin@example.com', '0234567890', 'Dentiste');
INSERT INTO medecin VALUES (null, 'Lefevre', 'Pierre', 'pierre.lefevre@example.com', '0345678901', 'Gynecologue');

INSERT INTO patient VALUES (null, 'Dubois', 'Alice', '123 Rue de la Paix', 'Paris', '0123456789', 'alice.dubois@example.com', '1990-05-15', '75001', 'Femme', 1);
INSERT INTO patient VALUES (null, 'Bernard', 'Paul', '456 Avenue des Fleurs', 'Nice', '0234567890', 'paul.bernard@example.com', '1985-10-20', '06000', 'Homme', 2);
INSERT INTO patient VALUES (null, 'Leroux', 'Marie', '789 Rue du Chateau', 'Lyon', '0345678901', 'marie.leroux@example.com', '1998-03-08', '69001', 'Femme', 3);