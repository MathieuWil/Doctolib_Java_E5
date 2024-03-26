package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Personne;

public class PanelProfil extends PanelPrincipal implements ActionListener {

    private JTextField nom, prenom, email, tel;
    private JPasswordField mdp1, mdp2;
    private JButton btEnregistrer, btModifier, btAnnuler, btRetour;

    private JPanel personneUpdateInfoPanel;
    private JPanel personneInfoPanel;
    private Personne unePersonne;

    private JLabel labelNom, labelPrenom, labelEmail, labelTel;

    public PanelProfil(Personne unePersonne) {
        super(new Color(4, 174, 197));
        this.unePersonne = unePersonne;

        // Section 1: Personne Information Form (initialisé comme invisible)
        personneInfoPanel = createPersonneInfoPanel(unePersonne);
        personneInfoPanel.setVisible(true);
        personneInfoPanel.setBackground(new Color(4, 174, 197));

        personneUpdateInfoPanel = createPersonneUpdatePanel();
        personneUpdateInfoPanel.setVisible(false);
        personneUpdateInfoPanel.setBackground(new Color(4, 174, 197));

        // Section 2: Buttons Panel
        JPanel buttonsPanel = createButtonsPanel();
        buttonsPanel.setBackground(new Color(4, 174, 197));

        // Main Panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Section 1: Personne Information Form
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(personneUpdateInfoPanel, gbc);

        // Section 2: Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttonsPanel, gbc);

        // Remplir les champs avec les informations du compte
        remplirChamps(unePersonne);
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        btModifier = new JButton("Modifier");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btModifier, gbc);
        btModifier.addActionListener(this);

        btEnregistrer = new JButton("Enregistrer");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(btEnregistrer, gbc);
        btEnregistrer.addActionListener(this);

        btAnnuler = new JButton("Annuler");
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(btAnnuler, gbc);
        btAnnuler.addActionListener(this);

        btRetour = new JButton("Retour");
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(btRetour, gbc);
        btRetour.addActionListener(this);

        // Cacher le bouton "Enregistrer" et "Annuler" par défaut
        btEnregistrer.setVisible(false);
        btAnnuler.setVisible(false);
        btRetour.setVisible(false);

        return panel;
    }

    private JPanel createPersonneInfoPanel(Personne unePersonne) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Titre
        JLabel labelTitre = new JLabel("----------- Profil -----------");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelTitre, gbc);

        // Nom
         labelNom = new JLabel("Nom : " + unePersonne.getNom());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelNom, gbc);

        // Prenom
         labelPrenom = new JLabel("Prenom : " + unePersonne.getPrenom());
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelPrenom, gbc);

        // Email
         labelEmail = new JLabel("Email : " + unePersonne.getEmail());
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelEmail, gbc);

        // Telephone
         labelTel = new JLabel("Telephone : " + unePersonne.getTel());
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelTel, gbc);

        // Texte décoration bas
        JLabel labelDeco = new JLabel("-------------------------------");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(labelDeco, gbc);

        // Création de mainPanel pour contenir le panneau avec des GridBagConstraints supplémentaires
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mainGBC = new GridBagConstraints();
        mainGBC.gridx = 0;
        mainGBC.gridy = 0;
        mainPanel.setBackground(new Color(4, 174, 197));
        mainPanel.add(panel, mainGBC);

        // Ajout du mainPanel à l'instance actuelle de PanelProfil
        GridBagConstraints thisGBC = new GridBagConstraints();
        thisGBC.gridx = 0;
        thisGBC.gridy = 0;
        this.add(mainPanel, thisGBC);

        // Mettre le fond en couleur 4, 174, 197
        panel.setBackground(new Color(4, 174, 197));

        return mainPanel;
    }

    public void updateAffichage (){
        
        // Nom
         labelNom.setText("Nom : " + unePersonne.getNom());
       

        // Prenom
         labelPrenom.setText("Prenom : " + unePersonne.getPrenom());
       
        // Email
        labelEmail.setText("Email : " + unePersonne.getEmail());
        
        // Telephone
        labelTel.setText("Telephone : " + unePersonne.getTel());
    }

    private JPanel createPersonneUpdatePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Nom
        JLabel labelNom = new JLabel("Nom : ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelNom, gbc);
        nom = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nom, gbc);

        // Prenom
        JLabel labelPrenom = new JLabel("Prenom : ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelPrenom, gbc);
        prenom = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(prenom, gbc);

        // Email
        JLabel labelEmail = new JLabel("Email : ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelEmail, gbc);
        email = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(email, gbc);

        // Telephone
        JLabel labelTel = new JLabel("Telephone : ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelTel, gbc);
        tel = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(tel, gbc);

        // Mot de passe
        JLabel labelMdp1 = new JLabel("Mot de passe : ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelMdp1, gbc);
        mdp1 = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(mdp1, gbc);

        // Confirmation mot de passe
        JLabel labelMdp2 = new JLabel("Confirmer mot de passe : ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(labelMdp2, gbc);
        mdp2 = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(mdp2, gbc);

        return panel;
    }

    private void remplirChamps(Personne unePersonne) {
        nom.setText(unePersonne.getNom());
        prenom.setText(unePersonne.getPrenom());
        email.setText(unePersonne.getEmail());
        tel.setText(unePersonne.getTel());
        mdp1.setText(unePersonne.getMdp());
        mdp2.setText(unePersonne.getMdp());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btEnregistrer) {
            if (nom.getText().equals("") || prenom.getText().equals("") || email.getText().equals("")
                    || tel.getText().equals("") || mdp1.getPassword().length == 0
                    || mdp2.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur",
				JOptionPane.ERROR_MESSAGE);
            } else if (!Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email.getText())) {
                JOptionPane.showMessageDialog(this, "Email invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (!Pattern.matches("^[0-9]{10}$", tel.getText())) {
                JOptionPane.showMessageDialog(this, "Telephone invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (!Pattern.matches(
                    "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*\\(\\)\\-\\+\\=\\[\\]\\{\\}\\|\\\\;:'\",<.>/?]).{8,}$",
                    new String(mdp1.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Mot de passe invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (!new String(mdp1.getPassword()).equals(new String(mdp2.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Les mots de passe ne correspondent pas", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Requete de mise à jour de la personne dans la base de données
                Personne personneMiseAJour = new Personne(this.unePersonne.getIdpersonne(), nom.getText(), prenom.getText(), email.getText(), new String(mdp1.getPassword()), tel.getText(), this.unePersonne.getRole());
                
                // Appeler la méthode hypothétique d'updatePersonneProfil dans le Controleur
                try {
                    Controleur.updatePersonneProfil(personneMiseAJour);
                    JOptionPane.showMessageDialog(this, "Profil enregistré avec succès", "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                           this.updateAffichage ();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Erreur lors de la mise à jour du profil dans la base de données", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }

            // Faire comme ci on cliquait sur le boutton retour
            // Rendre visible le bouton "Modifier"
            btModifier.setVisible(true);

            // Rendre invisible les boutons "Enregistrer", "Annuler" et "Retour"
            btEnregistrer.setVisible(false);
            btAnnuler.setVisible(false);
            btRetour.setVisible(false);

            // Rendre visible le formulaire d'information
            personneInfoPanel.setVisible(true);

            // Rendre invisible le formulaire de modification
            personneUpdateInfoPanel.setVisible(false);

        } else if (e.getSource() == this.btAnnuler) {
            viderChamps();
        } else if (e.getSource() == this.btRetour) {
            // Rendre visible le bouton "Modifier"
            btModifier.setVisible(true);

            // Rendre invisible les boutons "Enregistrer", "Annuler" et "Retour"
            btEnregistrer.setVisible(false);
            btAnnuler.setVisible(false);
            btRetour.setVisible(false);

            // Rendre visible le formulaire d'information
            personneInfoPanel.setVisible(true);

            // Rendre invisible le formulaire de modification
            personneUpdateInfoPanel.setVisible(false);
        } else if (e.getSource() == this.btModifier) {
            // Vérification "Etes-vous sûr de vouloir modifier votre profil ?"
            int choix = JOptionPane.showConfirmDialog(this, "Etes-vous sûr de vouloir modifier votre profil ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (choix == JOptionPane.NO_OPTION) {
                return;
            }
            
            // Rendre visible les boutons "Enregistrer" et "Annuler"
            btEnregistrer.setVisible(true);
            btAnnuler.setVisible(true);
            btRetour.setVisible(true);

            // Rendre invisible le bouton "Modifier"
            btModifier.setVisible(false);

            // Rendre invisible le formulaire d'information
            personneInfoPanel.setVisible(false);

            // Rendre visible le formulaire de modification
            personneUpdateInfoPanel.setVisible(true);
        }
    }

    private void viderChamps() {
        nom.setText("");
        prenom.setText("");
        email.setText("");
        tel.setText("");
        mdp1.setText("");
        mdp2.setText("");
    }
}
