package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Controleur;
import controleur.Patient;
import controleur.Tableau;

public class PanelPatients extends PanelPrincipal implements ActionListener, KeyListener {
    private JTextField txtNom, txtPrenom, txtAdresse, txtVille, txtTelephone, txtEmail, txtDateNaissance, txtCodePostal;
    private static JComboBox<String> cbSexe, cbMedecin;
    private JButton btEnregistrer, btAnnuler, btSupprimer;

    private JTable tablePatient; 
	private JScrollPane uneScroll; 
	private Tableau unTableau;

    private JLabel lbPatient;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");

    public PanelPatients() {
        super(new Color(4, 174, 197));

        // Section 0 : Initialisation des composants
        JPanel titrePanel = createTitrePanel();
        titrePanel.setBackground(new Color(4, 174, 197));

        // Section 1: Patient Information Form
        JPanel patientInfoPanel = createPatientInfoPanel();
        patientInfoPanel.setBackground(new Color(4, 174, 197));

        // Section 2: Table Display
        JPanel tablePanel = createTablePanel();
        tablePanel.setBackground(new Color(4, 174, 197));

        // Section 3: Buttons Panel
        JPanel buttonsPanel = createButtonsPanel();
        buttonsPanel.setBackground(new Color(4, 174, 197));

        // Main Panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Section 0: Titre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(titrePanel, gbc);

        // Section 1: Patient Information Form
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        add(patientInfoPanel, gbc);

        // Section 2: Table Display
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.8;
        gbc.weighty = 1.0;
        add(tablePanel, gbc);

        // Section 3: Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(buttonsPanel, gbc);
    }

    private JPanel createTitrePanel() {
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("<html><u><b>Gestion des patients</b></u></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alignement central du texte
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createPatientInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Nom
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nom: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNom = new JTextField(15);
        panel.add(txtNom, gbc);

        // Prénom
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Prénom: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtPrenom = new JTextField(15);
        panel.add(txtPrenom, gbc);

        // Adresse
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Adresse: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAdresse = new JTextField(15);
        panel.add(txtAdresse, gbc);

        // Code Postal
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Code Postal: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtCodePostal = new JTextField(15);
        panel.add(txtCodePostal, gbc);

        // Ville
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Ville: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtVille = new JTextField(15);
        panel.add(txtVille, gbc);

        // Téléphone
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Téléphone: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtTelephone = new JTextField(15);
        panel.add(txtTelephone, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Email: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtEmail = new JTextField(15);
        panel.add(txtEmail, gbc);

        // Date de Naissance
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Date de Naissance: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtDateNaissance = new JTextField(15);
        panel.add(txtDateNaissance, gbc);

        // Sexe
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Sexe: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cbSexe = new JComboBox<>(new String[]{"Homme", "Femme"});
        panel.add(cbSexe, gbc);

        // Médecin traitant
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(new JLabel("Médecin traitant: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cbMedecin = new JComboBox<>(Controleur.selectAllMedecins());
        panel.add(cbMedecin, gbc);

        return panel;
    }
    
    public static void actualiserCBMedecin() {
        cbMedecin.removeAllItems();
        for (String medecin : Controleur.selectAllMedecins()) {
            cbMedecin.addItem(medecin);
        }
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Ajout d'un tableau
        String entetes[] = {"ID", "Nom", "Prénom", "Adresse", "Code Postal", "Ville", "Téléphone", "Email", "Date de Naissance", "Sexe", "Médecin traitant"};
        this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
        this.tablePatient = new JTable(this.unTableau);
		this.uneScroll = new JScrollPane(this.tablePatient);
		this.uneScroll.setBounds(400, 80,400, 220);
		this.add(this.uneScroll);
        centerTableData();

        // Placement du panel filtre
        panelRech.setLayout(new GridLayout(1, 2));
        panelRech.add(new JLabel("Filtre :"));
        panelRech.setBackground(new Color(4, 174, 197));
        panelRech.add(txtFiltre);
        panelRech.add(btFiltre);
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        panel.add(panelRech, gbc);
        btFiltre.addActionListener(this);

        lbPatient = new JLabel("Nombre de patients insérés : "+unTableau.getRowCount());
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        panel.add(lbPatient, gbc);

        // Interdiction de déplacer les colonnes
        this.tablePatient.getTableHeader().setReorderingAllowed(false);

        //ajout d'un MouseListener pour suppression et modification. 
		this.tablePatient.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
                updateSupprimerButtonState();
                int numLigne = 0; 
				int idpatient = 0; 
				if (e.getClickCount()>=2) {
					numLigne = tablePatient.getSelectedRow(); 
					idpatient= Integer.parseInt(tablePatient.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce patient ?", 
							"Suppression Patient", JOptionPane.YES_NO_OPTION); 
					if (reponse == 0) {
						//suppression dans la BDD
						Controleur.deletePatient(idpatient);
						//suppression dans l'affichage de la table 
						unTableau.supprimerLigne(numLigne);
						lbPatient.setText("Nombre de patients disponibles :"+unTableau.getRowCount());
					}
				}
				else {
					numLigne = tablePatient.getSelectedRow();
                    idpatient = Integer.parseInt(tablePatient.getValueAt(numLigne, 0).toString());
                    String nom = tablePatient.getValueAt(numLigne, 1).toString();
                    String prenom = tablePatient.getValueAt(numLigne, 2).toString();
                    String adresse = tablePatient.getValueAt(numLigne, 3).toString();
                    String codePostal = tablePatient.getValueAt(numLigne, 4).toString();
                    String ville = tablePatient.getValueAt(numLigne, 5).toString();
                    String telephone = tablePatient.getValueAt(numLigne, 6).toString();
                    String email = tablePatient.getValueAt(numLigne, 7).toString();
                    String dateNaissance = tablePatient.getValueAt(numLigne, 8).toString();
                    String sexe = tablePatient.getValueAt(numLigne, 9).toString();
                    String medecin = tablePatient.getValueAt(numLigne, 10).toString();

					//remplissage du formulaire
                    txtNom.setText(nom);
                    txtPrenom.setText(prenom);
                    txtAdresse.setText(adresse);
                    txtCodePostal.setText(codePostal);
                    txtVille.setText(ville);
                    txtTelephone.setText(telephone);
                    txtEmail.setText(email);
                    txtDateNaissance.setText(dateNaissance);
                    cbSexe.setSelectedItem(sexe);
                    cbMedecin.setSelectedItem(medecin);

					// txtCategorie.setText(categorie);
					btEnregistrer.setText("Modifier");
				}
            }

        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        panel.add(uneScroll, gbc);

        this.txtFiltre.addKeyListener(this);

        return panel;
    }

    private Object[][] obtenirDonnees(String filtre) {
        ArrayList<Patient> lesPatients = Controleur.selectAllPatients(filtre); 
        Object [][] matrice = new Object[lesPatients.size()][11];
		int i = 0; 
		for (Patient unPatient : lesPatients) {
            matrice[i][0] = unPatient.getIdpatient();
            matrice[i][1] = unPatient.getNom();
            matrice[i][2] = unPatient.getPrenom();
            matrice[i][3] = unPatient.getAdresse();
            matrice[i][4] = unPatient.getCodePostal();
            matrice[i][5] = unPatient.getVille();
            matrice[i][6] = unPatient.getTelephone();
            matrice[i][7] = unPatient.getEmail();
            matrice[i][8] = unPatient.getDateNaissance();
            matrice[i][9] = unPatient.getSexe();
            matrice[i][10] = unPatient.getIdMedecin();
			i++;
		}
		return matrice;
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Boutons
        btAnnuler = new JButton("Annuler");
        btEnregistrer = new JButton("Enregistrer");
        btSupprimer = new JButton("Supprimer");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(btEnregistrer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(btAnnuler, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(btSupprimer, gbc);
        this.btSupprimer.setEnabled(false);

        // Ajout des listeners
        btEnregistrer.addActionListener(this);
        btAnnuler.addActionListener(this);
        btSupprimer.addActionListener(this);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.btAnnuler) {
			this.viderChamps();
            this.btSupprimer.setEnabled(false);
            this.btEnregistrer.setText("Enregistrer");

            // Déselectionner la ligne sélectionnée
            tablePatient.clearSelection();
		} else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			boolean ok = true;
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresse = this.txtAdresse.getText();
            String codePostal = this.txtCodePostal.getText();
            String ville = this.txtVille.getText();
            String telephone = this.txtTelephone.getText();
            String email = this.txtEmail.getText();
            String dateNaissance = this.txtDateNaissance.getText();
            String sexe = PanelPatients.cbSexe.getSelectedItem().toString();

            if (nom.equals("") || prenom.equals("") || adresse.equals("")|| codePostal.equals("") || ville.equals("") || telephone.equals("") || email.equals("") || dateNaissance.equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (!Pattern.matches("^[0-9]{10}$", telephone)) {
                JOptionPane.showMessageDialog(this, "Le numéro de téléphone doit être composé de 10 chiffres", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (!Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email)) {
                JOptionPane.showMessageDialog(this, "L'adresse email n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (!Pattern.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$", dateNaissance)) {
                JOptionPane.showMessageDialog(this, "La date de naissance doit être au format AAAA/MM/JJ", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (!Pattern.matches("^[0-9]{5}$", codePostal)) {
                JOptionPane.showMessageDialog(this, "Le code postal doit être composé de 5 chiffres", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            }

            int idmedecin = 0; 
            String chaine = PanelPatients.cbMedecin.getSelectedItem().toString();
            String tab [] = chaine.split(" "); 
            idmedecin = Integer.parseInt(tab[0]);

			//on vérifie les données avant insertion dans la base 
			if (ok) {
				//on enregistre le new patient dans la base 
				Patient unPatient = new Patient(nom, prenom, adresse, codePostal, ville, telephone, email, dateNaissance, sexe, idmedecin);
				Controleur.ajouterPatient(unPatient); 
			
				//récupération de l'ID donné par mysql 
				unPatient = Controleur.selectWherePatient(nom, prenom, adresse, codePostal, ville, telephone, email, dateNaissance, sexe); 
			
				JOptionPane.showMessageDialog(this, "Patient inséré avec succés dans la BDD");

				//insertion dans l'affichage graphique 
				Object ligne[]= {unPatient.getIdpatient(), nom, prenom, adresse, codePostal, ville, telephone, email, dateNaissance, sexe, idmedecin};
				this.unTableau.ajouterLigne(ligne);
				lbPatient.setText("Nombre de patient disponibles :"+unTableau.getRowCount());
			
                // Actualisation des combobox dans les autres panels
                PanelRendezVous.actualiserCBPatient();
                PanelPrescriptions.actualiserCBPatient();

                this.btSupprimer.setEnabled(false);

				this.viderChamps();
			}
		} else if (e.getSource()== this.btFiltre) {
			String filtre = this.txtFiltre.getText(); 
			Object matrice [][ ] = this.obtenirDonnees(filtre); 
			this.unTableau.setDonnees(matrice);
        } else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresse = this.txtAdresse.getText();
            String codePostal = this.txtCodePostal.getText();
            String ville = this.txtVille.getText();
            String telephone = this.txtTelephone.getText();
            String email = this.txtEmail.getText();
            String dateNaissance = this.txtDateNaissance.getText();
            String sexe = PanelPatients.cbSexe.getSelectedItem().toString();
            String chaine = PanelPatients.cbMedecin.getSelectedItem().toString();
            String tab [] = chaine.split(" "); 
            int idmedecin = Integer.parseInt(tab[0]);

            int numLigne = 0 ; 
			int idPatient = 0; 
			numLigne = tablePatient.getSelectedRow();
			idPatient= Integer.parseInt(tablePatient.getValueAt(numLigne, 0).toString());

            Patient unPatient = new Patient(idPatient, nom, prenom, adresse, ville, telephone, email, dateNaissance, codePostal, sexe, idmedecin); 
			//modification dans la base de données 
			Controleur.updatePatient(unPatient);
			//modification dans l'affichage 
			Object ligne []= {idPatient, nom, prenom, adresse, codePostal, ville, telephone, email, dateNaissance, sexe, idmedecin};
		    this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectuée");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");

            // Actualisation des combobox dans les autres panels
            PanelRendezVous.actualiserCBPatient();
            PanelPrescriptions.actualiserCBPatient();
            
		} 
        else if (e.getSource() == this.btSupprimer) {
            int numLigne = 0;
            int idpatient = 0;
            numLigne = tablePatient.getSelectedRow();
            idpatient = Integer.parseInt(tablePatient.getValueAt(numLigne, 0).toString());
            int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce patient ?", "Suppression Patient", JOptionPane.YES_NO_OPTION);
            if (reponse == 0) {
                //suppression dans la BDD
                Controleur.deletePatient(idpatient);
                //suppression dans l'affichage de la table
                unTableau.supprimerLigne(numLigne);
                lbPatient.setText("Nombre de patients disponibles :" + unTableau.getRowCount());
                this.btSupprimer.setEnabled(false);
                this.viderChamps();
			    this.btEnregistrer.setText("Enregistrer");

                // Actualisation des combobox dans les autres panels
                PanelRendezVous.actualiserCBPatient();
                PanelPrescriptions.actualiserCBPatient();
            }
        }
    }

    private void updateSupprimerButtonState() {
        int numLigne = tablePatient.getSelectedRow();
        btSupprimer.setEnabled(numLigne >= 0); // Active le bouton si une ligne est sélectionnée
    }

    private void viderChamps() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtAdresse.setText("");
        txtVille.setText("");
        txtTelephone.setText("");
        txtEmail.setText("");
        txtDateNaissance.setText("");
        txtCodePostal.setText("");
        cbSexe.setSelectedIndex(0);
        cbMedecin.setSelectedIndex(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            String filtre = this.txtFiltre.getText(); 
			Object matrice [][ ] = this.obtenirDonnees(filtre); 
			this.unTableau.setDonnees(matrice);
        } else if (e.getKeyCode() == KeyEvent.VK_DELETE){
            this.txtFiltre.setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    private void centerTableData() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tablePatient.getColumnCount(); i++) {
            tablePatient.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
