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
import controleur.Medecin;
import controleur.Tableau;

public class PanelMedecins extends PanelPrincipal implements ActionListener, KeyListener {

    private JTextField txtNom, txtPrenom, txtTel, txtMail;
    private JComboBox<String> cbSpecialite;
    private JButton btEnregistrer, btAnnuler, btSupprimer;

    private JTable tableMedecin; 
	private JScrollPane uneScroll; 
	private Tableau unTableau;

    private JLabel lbMedecin;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");

    public PanelMedecins() {
        super(new Color(4, 174, 197));

        // Section 0: Titre
        JPanel titrePanel = createTitrePanel();
        titrePanel.setBackground(new Color(4, 174, 197));

        // Section 1: Medecin Information Form
        JPanel medecinInfoPanel = createMedecinInfoPanel();
        medecinInfoPanel.setBackground(new Color(4, 174, 197));

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
        add(titrePanel, gbc);

        // Section 1: Médecin Information Form
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        add(medecinInfoPanel, gbc);

        // Section 2: Table Display
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.6;
        add(tablePanel, gbc);

        // Section 3: Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        add(buttonsPanel, gbc);
    }

    private JPanel createTitrePanel() {
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("<html><u><b>Gestion des medecins</b></u></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alignement central du texte
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createMedecinInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNom = new JLabel("Nom : ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNom, gbc);

        txtNom = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNom, gbc);

        JLabel lblPrenom = new JLabel("Prénom : ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPrenom, gbc);

        txtPrenom = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtPrenom, gbc);

        JLabel lblMail = new JLabel("Mail : ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblMail, gbc);

        txtMail = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtMail, gbc);

        JLabel lblTel = new JLabel("Téléphone : ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblTel, gbc);

        txtTel = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtTel, gbc);

        JLabel lblSpecialite = new JLabel("Spécialité : ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblSpecialite, gbc);

        cbSpecialite = new JComboBox<>(Controleur.selectAllProfessions().toArray(new String[0]));
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(cbSpecialite, gbc);
        
        return panel;
    }

    private JPanel createTablePanel() { 
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Ajout d'un tableau
        String entetes [] = {"ID", "Nom", "Prénom", "Email", "Téléphone", "Spécialité"};
		this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
		this.tableMedecin = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.tableMedecin); 
		this.uneScroll.setBounds(400, 80,400, 220);
		this.add(this.uneScroll);
        centerTableData();

        //placement du panel filtre 
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

        lbMedecin = new JLabel("Nombre de medecins disponibles : "+unTableau.getRowCount());
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        panel.add(lbMedecin, gbc);

        // Interdiction de déplacer les colonnes
        this.tableMedecin.getTableHeader().setReorderingAllowed(false);

        //ajout d'un MouseListener pour suppression et modification. 
		this.tableMedecin.addMouseListener(new MouseListener() {
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
				int idmedecin = 0; 
				if (e.getClickCount()>=2) {
					numLigne = tableMedecin.getSelectedRow(); 
					idmedecin= Integer.parseInt(tableMedecin.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce médecin ?", 
							"Suppression Médecin", JOptionPane.YES_NO_OPTION); 
					if (reponse == 0) {
						//suppression dans la BDD
						Controleur.deleteMedecin(idmedecin);
						//suppression dans l'affichage de la table 
						unTableau.supprimerLigne(numLigne);
						lbMedecin.setText("Nombre de medecins disponibles :"+unTableau.getRowCount());
					}
				}
				else {
					numLigne = tableMedecin.getSelectedRow();
                    idmedecin = Integer.parseInt(tableMedecin.getValueAt(numLigne, 0).toString());
                    String nom = tableMedecin.getValueAt(numLigne, 1).toString();
                    String prenom = tableMedecin.getValueAt(numLigne, 2).toString();
                    String tel = tableMedecin.getValueAt(numLigne, 3).toString();
                    String email = tableMedecin.getValueAt(numLigne, 4).toString();
                    String specialite = tableMedecin.getValueAt(numLigne, 5).toString();

					//remplissage du formulaire 
					txtNom.setText(nom);
                    txtPrenom.setText(prenom);
                    txtTel.setText(tel);
                    txtMail.setText(email);
                    cbSpecialite.setSelectedItem(specialite);
                    

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

    public Object [][] obtenirDonnees (String filtre){
		ArrayList<Medecin> lesMedecins = Controleur.selectAllMedecins(filtre); 
		Object [][] matrice = new Object[lesMedecins.size()][6];
		int i = 0; 
		for (Medecin unMedecin : lesMedecins) {
			matrice[i][0] = unMedecin.getIdmedecin();
            matrice[i][1] = unMedecin.getNom();
            matrice[i][2] = unMedecin.getPrenom();
            matrice[i][3] = unMedecin.getMail();
            matrice[i][4] = unMedecin.getTel();
            matrice[i][5] = unMedecin.getSpecialite();
			i++;
		}
		return matrice;
	}

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        btEnregistrer = new JButton("Enregistrer");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btEnregistrer, gbc);
        btEnregistrer.addActionListener(this);

        btAnnuler = new JButton("Annuler");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(btAnnuler, gbc);
        btAnnuler.addActionListener(this);

        btSupprimer = new JButton("Supprimer");
        this.btSupprimer.setEnabled(false);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(btSupprimer, gbc);
        btSupprimer.addActionListener(this);

        return panel;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btAnnuler) {
			this.viderChamps();
            this.btEnregistrer.setText("Enregistrer");
            this.btSupprimer.setEnabled(false);

            // Déselectionner la ligne sélectionnée
            tableMedecin.clearSelection();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			boolean ok = true;
			String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String tel = this.txtTel.getText();
            String email = this.txtMail.getText();
            String specialite = this.cbSpecialite.getSelectedItem().toString();

            if (nom.equals("") || prenom.equals("") || tel.equals("") || email.equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (Controleur.selectWhereMedecin(nom, prenom, email, tel, specialite) != null) {
                JOptionPane.showMessageDialog(this, "Ce médecin existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (!Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", email)) {
                JOptionPane.showMessageDialog(this, "Email invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (!Pattern.matches("^[0-9]{10}$", tel)) {
                JOptionPane.showMessageDialog(this, "Téléphone invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            }

			//on vérifie les données avant insertion dans la base 
			if (ok) {
				//on enregistre le new medecin dans la base 
				Medecin unMedecin = new Medecin(nom, prenom, tel, email, specialite);
				Controleur.insertMedecin(unMedecin); 
			
				//récupération de l'ID donné par mysql 
				unMedecin = Controleur.selectWhereMedecin(nom, prenom, email, tel, specialite); 
			
				JOptionPane.showMessageDialog(this, "Medecin inséré avec succés dans la BDD");
				//insertion dans l'affichage graphique 
				Object ligne[]= {unMedecin.getIdmedecin(), nom, prenom, tel, email, specialite};
				this.unTableau.ajouterLigne(ligne);
				lbMedecin.setText("Nombre de médecins disponibles :"+unTableau.getRowCount());
                
                //actualisation des combobox dans les autres panels
                PanelPatients.actualiserCBMedecin();
                PanelRendezVous.actualiserCBMedecin();
                PanelPrescriptions.actualiserCBMedecin();
                PanelRendezVous.actualiserCBPatient();
                PanelPrescriptions.actualiserCBPatient();

                this.btSupprimer.setEnabled(false);

				this.viderChamps();
			}
		}
		else if (e.getSource()== this.btFiltre)
		{
			String filtre = this.txtFiltre.getText(); 
			Object matrice [][ ] = this.obtenirDonnees(filtre); 
			this.unTableau.setDonnees(matrice);
		}
		
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String tel = this.txtTel.getText();
            String email = this.txtMail.getText();
            String specialite = this.cbSpecialite.getSelectedItem().toString();
			
			int numLigne = 0 ; 
			int idmedecin = 0; 
			numLigne = tableMedecin.getSelectedRow();
			idmedecin= Integer.parseInt(tableMedecin.getValueAt(numLigne, 0).toString());

            Medecin unMedecin = new Medecin(idmedecin, nom, prenom, tel, email, specialite); 
			//modification dans la base de données 
			Controleur.updateMedecin(unMedecin);
			//modification dans l'affichage 
			Object ligne []= {idmedecin, nom, prenom, tel, email, specialite};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectuée");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");

            //actualisation des combobox dans les autres panels
            PanelPatients.actualiserCBMedecin();
            PanelRendezVous.actualiserCBMedecin();
            PanelPrescriptions.actualiserCBMedecin();
            
		} else if (e.getSource() == this.btSupprimer) {
            int numLigne = tableMedecin.getSelectedRow();
            int idmedecin = Integer.parseInt(tableMedecin.getValueAt(numLigne, 0).toString());
            int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimer ce médecin ?", "Suppression Médecin", JOptionPane.YES_NO_OPTION);
            if (reponse == 0) {
                // Suppression dans la BDD
                Controleur.deleteMedecin(idmedecin);
                // Suppression dans l'affichage de la table
                unTableau.supprimerLigne(numLigne);
                lbMedecin.setText("Nombre de medecins disponibles :"+unTableau.getRowCount());
                this.btSupprimer.setEnabled(false);
                this.viderChamps();
			    this.btEnregistrer.setText("Enregistrer");

                //actualisation des combobox dans les autres panels
                PanelPatients.actualiserCBMedecin();
                PanelRendezVous.actualiserCBMedecin();
                PanelPrescriptions.actualiserCBMedecin();
            }
        }
	}

    private void updateSupprimerButtonState() {
        int numLigne = tableMedecin.getSelectedRow();
        btSupprimer.setEnabled(numLigne >= 0); // Active le bouton si une ligne est sélectionnée
    }

    private void viderChamps() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtTel.setText("");
        txtMail.setText("");
        cbSpecialite.setSelectedIndex(0);
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
    
        for (int i = 0; i < tableMedecin.getColumnCount(); i++) {
            tableMedecin.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
