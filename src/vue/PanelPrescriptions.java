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
import controleur.Prescription;
import controleur.Tableau;

public class PanelPrescriptions extends PanelPrincipal implements ActionListener, KeyListener {

    private JTextField txtDatePrescription, txtMedicament;
    private static JComboBox<String> cbPatient, cbMedecin;
    private JButton btEnregistrer, btAnnuler, btSupprimer;

    private JTable tablePrescription; 
	private JScrollPane uneScroll; 
	private Tableau unTableau;

    private JLabel lbPrescription;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");

    public PanelPrescriptions() {
        super(new Color(4, 174, 197));

        // Section 0: Titre
        JPanel titrePanel = createTitrePanel();
        titrePanel.setBackground(new Color(4, 174, 197));

        // Section 1: Prescription Information Form
        JPanel prescriptionInfoPanel = createPrescriptionInfoPanel();
        prescriptionInfoPanel.setBackground(new Color(4, 174, 197));

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
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(titrePanel, gbc);

        // Section 1: Rdv Information Form
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        add(prescriptionInfoPanel, gbc);

        // Section 2: Table Display
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.8;
        add(tablePanel, gbc);

        // Section 3: Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonsPanel, gbc);
    }

    private JPanel createTitrePanel() {
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("<html><u><b>Gestion des prescriptions</b></u></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alignement central du texte
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createPrescriptionInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Date prescription : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtDatePrescription = new JTextField(15);
        panel.add(txtDatePrescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Médicament : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtMedicament = new JTextField(15);
        panel.add(txtMedicament, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Patient : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cbPatient = new JComboBox<>(Controleur.selectAllPatients());
        panel.add(cbPatient, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Médecin : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
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

    public static void actualiserCBPatient() {
        cbPatient.removeAllItems();
        for (String patient : Controleur.selectAllPatients()) {
            cbPatient.addItem(patient);
        }
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Ajout d'un tableau
        String entetes[] = {"ID", "Date", "Médicament", "Patient", "Médecin"};
        this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
        this.tablePrescription = new JTable(this.unTableau);
		this.uneScroll = new JScrollPane(this.tablePrescription);
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

        lbPrescription = new JLabel("Nombre de prescriptions insérés : "+unTableau.getRowCount());
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        panel.add(lbPrescription, gbc);

        // Interdiction de déplacer les colonnes
        this.tablePrescription.getTableHeader().setReorderingAllowed(false);

        //ajout d'un MouseListener pour suppression et modification. 
		this.tablePrescription.addMouseListener(new MouseListener() {
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
				int idprescription = 0; 
				if (e.getClickCount()>=2) {
					numLigne = tablePrescription.getSelectedRow(); 
					idprescription= Integer.parseInt(tablePrescription.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce prescription ?", 
							"Suppression prescription", JOptionPane.YES_NO_OPTION); 
					if (reponse == 0) {
						//suppression dans la BDD
						Controleur.deletePrescription(idprescription);
						//suppression dans l'affichage de la table 
						unTableau.supprimerLigne(numLigne);
						lbPrescription.setText("Nombre de prescriptions disponibles :"+unTableau.getRowCount());
					}
				}
				else {
					numLigne = tablePrescription.getSelectedRow();
                    idprescription = Integer.parseInt(tablePrescription.getValueAt(numLigne, 0).toString());
                    String datePrescription = tablePrescription.getValueAt(numLigne, 1).toString();
                    String medicament = tablePrescription.getValueAt(numLigne, 2).toString();
                    String patient = tablePrescription.getValueAt(numLigne, 3).toString();
                    String medecin = tablePrescription.getValueAt(numLigne, 4).toString();

					//remplissage du formulaire
                    txtDatePrescription.setText(datePrescription);
                    txtMedicament.setText(medicament);
                    cbPatient.setSelectedItem(patient);
                    cbMedecin.setSelectedItem(medecin);

					// txtCategorie.setText(categorie);
					btEnregistrer.setText("Modifier");
				}
            }

        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        panel.add(uneScroll, gbc);

        this.txtFiltre.addKeyListener(this);

        return panel;
    }

    private Object[][] obtenirDonnees(String filtre) {
        ArrayList<Prescription> lesPrescriptions = Controleur.selectAllPrescriptions(filtre); 
        Object [][] matrice = new Object[lesPrescriptions.size()][5];
		int i = 0; 
		for (Prescription unPatient : lesPrescriptions) {
            matrice[i][0] = unPatient.getIdprescription();
            matrice[i][1] = unPatient.getDateprescription();
            matrice[i][2] = unPatient.getMedicament();
            matrice[i][3] = unPatient.getIdpatient();
            matrice[i][4] = unPatient.getIdmedecin();
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
            tablePrescription.clearSelection();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			boolean ok = true;
            String datePrescription = this.txtDatePrescription.getText();
            String medicament = this.txtMedicament.getText();
            
            String chaine = this.cbPatient.getSelectedItem().toString();
            String tab [] = chaine.split(" ");
            int idpatient = Integer.parseInt(tab[0]);
            
            chaine = this.cbMedecin.getSelectedItem().toString();
            tab = chaine.split(" ");
            int idmedecin = Integer.parseInt(tab[0]);

            if (datePrescription.equals("") || medicament.equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", datePrescription)) {
                JOptionPane.showMessageDialog(this, "Le format de la date doit être YYYY-MM-DD", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            }
            
            
			//on vérifie les données avant insertion dans la base 
			if (ok) {
				//on enregistre le new patient dans la base 
				Prescription unePrescription = new Prescription(datePrescription, medicament, idpatient, idmedecin);
				Controleur.ajouterPrescription(unePrescription); 
			
				//récupération de l'ID donné par mysql 
				unePrescription = Controleur.selectWherePrescription(datePrescription, medicament, idpatient, idmedecin);
			
				JOptionPane.showMessageDialog(this, "Patient inséré avec succés dans la BDD");

				//insertion dans l'affichage graphique 
				Object ligne[]= {unePrescription.getIdprescription(), datePrescription, medicament, idpatient, idmedecin};
				this.unTableau.ajouterLigne(ligne);
				lbPrescription.setText("Nombre de patient disponibles :"+unTableau.getRowCount());
			
				this.viderChamps();
			}
		}
		else if (e.getSource()== this.btFiltre) {
			String filtre = this.txtFiltre.getText(); 
			Object matrice [][ ] = this.obtenirDonnees(filtre); 
			this.unTableau.setDonnees(matrice);
		}
		
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String datePrescription = this.txtDatePrescription.getText();
            String medicament = this.txtMedicament.getText();

            String chainePatient = this.cbPatient.getSelectedItem().toString();
            String tabPatient [] = chainePatient.split(" ");
            int idpatient = Integer.parseInt(tabPatient[0]);


            String chaineMedecin = this.cbMedecin.getSelectedItem().toString();
            String tabMedecin [] = chaineMedecin.split(" "); 
            int idmedecin = Integer.parseInt(tabMedecin[0]);

			int numLigne = 0 ;
            int idPrescription = 0;
			numLigne = tablePrescription.getSelectedRow();
			idPrescription= Integer.parseInt(tablePrescription.getValueAt(numLigne, 0).toString());

			//Instanciation d'un Patient 
			Prescription unePrescription = new Prescription(idPrescription, datePrescription, medicament, idpatient, idmedecin); 
			//modification dans la base de données 
			Controleur.updatePrescription(unePrescription);
			//modification dans l'affichage 
			Object ligne []= {idPrescription, datePrescription, medicament, idpatient, idmedecin};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectuée");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		} else if (e.getSource() == this.btSupprimer) {
            int numLigne = 0;
            int idPrescription = 0;
            numLigne = tablePrescription.getSelectedRow();
            idPrescription = Integer.parseInt(tablePrescription.getValueAt(numLigne, 0).toString());
            int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer ce prescription ?", "Suppression prescription", JOptionPane.YES_NO_OPTION);
            if (reponse == 0) {
                // Suppression dans la BDD
                Controleur.deletePrescription(idPrescription);
                // Suppression dans l'affichage de la table
                unTableau.supprimerLigne(numLigne);
                lbPrescription.setText("Nombre de prescriptions présentes dans la DB :" + unTableau.getRowCount());
                this.btSupprimer.setEnabled(false);
                this.viderChamps();
			    this.btEnregistrer.setText("Enregistrer");
            }
        }
    }

    private void updateSupprimerButtonState() {
        int numLigne = tablePrescription.getSelectedRow();
        btSupprimer.setEnabled(numLigne >= 0); // Active le bouton si une ligne est sélectionnée
    }

    private void viderChamps() {
        txtDatePrescription.setText("");
        txtMedicament.setText("");
        cbPatient.setSelectedIndex(0);
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

        for (int i = 0; i < tablePrescription.getColumnCount(); i++) {
            tablePrescription.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
