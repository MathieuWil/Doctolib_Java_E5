DROP DATABASE IF EXISTS rdv_medecin;
CREATE DATABASE rdv_medecin;
USE rdv_medecin;

CREATE TABLE Patient (
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
    FOREIGN KEY (idmedecin) REFERENCES Medecin(idmedecin)
);

CREATE TABLE Medecin (
    idmedecin INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    tel VARCHAR(50) NOT NULL,
    specialite VARCHAR(50) NOT NULL,
    PRIMARY KEY (idmedecin)
);

CREATE TABLE RendezVous (
    idrendezvous INT(3) NOT NULL AUTO_INCREMENT,
    daterdv DATE NOT NULL,
    heure TIME NOT NULL,
    etat VARCHAR(50) NOT NULL,
    idpatient INT(3) NOT NULL,
    idmedecin INT(3) NOT NULL,
    PRIMARY KEY (idrendezvous),
    FOREIGN KEY (idpatient) REFERENCES Patient(idpatient),
    FOREIGN KEY (idmedecin) REFERENCES Medecin(idmedecin)
);

CREATE TABLE Prescription (
    idprescription INT(3) NOT NULL AUTO_INCREMENT,
    dateprescription DATE NOT NULL,
    medicament VARCHAR(100) NOT NULL, 
    idpatient INT(3) NOT NULL,
    idmedecin INT(3) NOT NULL,
    PRIMARY KEY (idprescription),
    FOREIGN KEY (idpatient) REFERENCES Patient(idpatient),
    FOREIGN KEY (idmedecin) REFERENCES Medecin(idmedecin)
);

CREATE TABLE Personne (
    idpersonne INT(3) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    mdp VARCHAR(50) NOT NULL,
    tel VARCHAR(50) NOT NULL,
    role ENUM ('Patient','Medecin','Admin') NOT NULL,
    PRIMARY KEY (idpersonne)
);

CREATE TABLE Professions (
    idprofession INT(3) NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(50) NOT NULL,
    PRIMARY KEY (idprofession)
);

INSERT INTO Professions VALUES (null, 'Cardiologue');
INSERT INTO Professions VALUES (null, 'Dentiste');
INSERT INTO Professions VALUES (null, 'Dermatologue');
INSERT INTO Professions VALUES (null, 'Gynecologue');
INSERT INTO Professions VALUES (null, 'Ophtalmologue');
INSERT INTO Professions VALUES (null, 'Pediatre');
INSERT INTO Professions VALUES (null, 'Psychiatre');
INSERT INTO Professions VALUES (null, 'Radiologue');
INSERT INTO Professions VALUES (null, 'Urologue');
INSERT INTO Professions VALUES (null, 'Chirurgien');
INSERT INTO Professions VALUES (null, 'Orthopediste');
INSERT INTO Professions VALUES (null, 'Oncologue');
INSERT INTO Professions VALUES (null, 'Neurologue');
INSERT INTO Professions VALUES (null, 'Pneumologue');
INSERT INTO Professions VALUES (null, 'Endocrinologue');
INSERT INTO Professions VALUES (null, 'Rhumatologue');
INSERT INTO Professions VALUES (null, 'Nephrologue');
INSERT INTO Professions VALUES (null, 'Hematologue');
INSERT INTO Professions VALUES (null, 'Allergologue');
INSERT INTO Professions VALUES (null, 'Anesthesiste');
INSERT INTO Professions VALUES (null, 'Chirurgien-dentiste');
INSERT INTO Professions VALUES (null, 'Infirmier');
INSERT INTO Professions VALUES (null, 'Sage-femme');
INSERT INTO Professions VALUES (null, 'Kinesitherapeute');
INSERT INTO Professions VALUES (null, 'Orthophoniste');
INSERT INTO Professions VALUES (null, 'Psychologue');
INSERT INTO Professions VALUES (null, 'Dieteticien');
INSERT INTO Professions VALUES (null, 'Podologue');
INSERT INTO Professions VALUES (null, 'Opticien');
INSERT INTO Professions VALUES (null, 'Audioprothesiste');
INSERT INTO Professions VALUES (null, 'Orthoptiste');
INSERT INTO Professions VALUES (null, 'Prothesiste-dentaire');
INSERT INTO Professions VALUES (null, 'Assistant-medical');
INSERT INTO Professions VALUES (null, 'Assistant-social');
INSERT INTO Professions VALUES (null, 'Ambulancier');
INSERT INTO Professions VALUES (null, 'Pompier');
INSERT INTO Professions VALUES (null, 'Pharmacien');
INSERT INTO Professions VALUES (null, 'Psychomotricien');
INSERT INTO Professions VALUES (null, 'Autre');

INSERT INTO Personne VALUES (null, 'Walker', 'Maxence', 'mwalker@gmail.com', 'Superb@pt95!', '0615141213', 'Patient');
INSERT INTO Personne VALUES (null, 'Wilkosz', 'Matthieu', 'mwilkosz@gmail.com', 'Superb@pt95!', '0615141213', 'Medecin');
INSERT INTO Personne VALUES (null, 'Lucbert', 'Baptiste', 'blucbert@gmail.com', 'Superb@pt95!', '0615141213', 'Admin');
INSERT INTO Personne VALUES (null, 'Youssoufa', 'Ilyes', 'iyoussoufa@gmail.com', 'Superb@pt95!', '0615141213', 'Admin');

INSERT INTO Medecin VALUES (null, 'Dupont', 'Jean', 'jean.dupont@example.com', '0123456789', 'Cardiologue');
INSERT INTO Medecin VALUES (null, 'Martin', 'Sophie', 'sophie.martin@example.com', '0234567890', 'Dentiste');
INSERT INTO Medecin VALUES (null, 'Lefevre', 'Pierre', 'pierre.lefevre@example.com', '0345678901', 'Gynecologue');

INSERT INTO Patient VALUES (null, 'Dubois', 'Alice', '123 Rue de la Paix', 'Paris', '0123456789', 'alice.dubois@example.com', '1990-05-15', '75001', 'Femme', 1);
INSERT INTO Patient VALUES (null, 'Bernard', 'Paul', '456 Avenue des Fleurs', 'Nice', '0234567890', 'paul.bernard@example.com', '1985-10-20', '06000', 'Homme', 2);
INSERT INTO Patient VALUES (null, 'Leroux', 'Marie', '789 Rue du Chateau', 'Lyon', '0345678901', 'marie.leroux@example.com', '1998-03-08', '69001', 'Femme', 3);