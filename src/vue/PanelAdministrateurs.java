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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableCellRenderer;


import controleur.Controleur;
import controleur.Personne;
import controleur.Tableau;

public class PanelAdministrateurs extends PanelPrincipal implements ActionListener, KeyListener {

    private JTextField txtNom, txtPrenom, txtEmail, txtTel, txtMdp;
    private JComboBox<String> cbRole;
    private JButton btEnregistrer, btAnnuler, btSupprimer;

    private JTable tablePersonne; 
	private JScrollPane uneScroll; 
	private Tableau unTableau;

    private JLabel lbPersonne;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");

    public PanelAdministrateurs() {
        super(new Color(4, 174, 197));

        // Section 0: Titre
        JPanel titrePanel = createTitrePanel();
        titrePanel.setBackground(new Color(4, 174, 197));

        // Section 1: Patient Information Form
        JPanel adminInfoPanel = createAdminInfoPanel();
        adminInfoPanel.setBackground(new Color(4, 174, 197));

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

        // Section 1: Personne Information Form
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        add(adminInfoPanel, gbc);

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
        add(buttonsPanel, gbc);
    }

    private JPanel createTitrePanel() {
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("<html><u><b>Gestion des rendez-vous</b></u></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alignement central du texte
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createAdminInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNom = new JLabel("Nom");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNom, gbc);

        txtNom = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNom, gbc);

        JLabel lblPrenom = new JLabel("Prénom");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPrenom, gbc);

        txtPrenom = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtPrenom, gbc);

        JLabel lblEmail = new JLabel("Email");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblEmail, gbc);

        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtEmail, gbc);

        JLabel lblTel = new JLabel("Téléphone");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblTel, gbc);

        txtTel = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtTel, gbc);

        JLabel lblRole = new JLabel("Rôle");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblRole, gbc);

        String[] roles = {"Patient","Medecin","Admin"};
        cbRole = new JComboBox<>(roles);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(cbRole, gbc);

        JLabel lblMdp = new JLabel("Mot de passe");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblMdp, gbc);

        txtMdp = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(txtMdp, gbc);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
    
        // Ajout d'un tableau
        String entetes[] = {"ID", "Nom", "Prénom", "Email", "Téléphone", "Rôle"};
        this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
        this.tablePersonne = new JTable(this.unTableau);
		this.uneScroll = new JScrollPane(this.tablePersonne);
		this.uneScroll.setPreferredSize (this.tablePersonne.getPreferredSize());
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

        lbPersonne = new JLabel("Nombre de personnes insérés : "+unTableau.getRowCount());
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        panel.add(lbPersonne, gbc);

        // Interdiction de déplacer les colonnes
        this.tablePersonne.getTableHeader().setReorderingAllowed(false);

        //ajout d'un MouseListener pour suppression et modification. 
		this.tablePersonne.addMouseListener(new MouseListener() {
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
				int idpersonne = 0;
				if (e.getClickCount()>=2) {
					numLigne = tablePersonne.getSelectedRow(); 
					idpersonne= Integer.parseInt(tablePersonne.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette personne ?", 
							"Suppression Personne", JOptionPane.YES_NO_OPTION); 
					if (reponse == 0) {
						//suppression dans la BDD
						Controleur.deletePersonne(idpersonne);
						//suppression dans l'affichage de la table 
						unTableau.supprimerLigne(numLigne);
						lbPersonne.setText("Nombre de personnes dans la DB :"+unTableau.getRowCount());
					}
				}
				else {
					numLigne = tablePersonne.getSelectedRow();
                    idpersonne = Integer.parseInt(tablePersonne.getValueAt(numLigne, 0).toString());
                    String nom = tablePersonne.getValueAt(numLigne, 1).toString();
                    String prenom = tablePersonne.getValueAt(numLigne, 2).toString();
                    String email = tablePersonne.getValueAt(numLigne, 3).toString();
                    String tel = tablePersonne.getValueAt(numLigne, 4).toString();
                    String role = tablePersonne.getValueAt(numLigne, 5).toString();

					//remplissage du formulaire
                    txtNom.setText(nom);
                    txtPrenom.setText(prenom);
                    txtEmail.setText(email);
                    txtTel.setText(tel);
                    cbRole.setSelectedItem(role);

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
        ArrayList<Personne> lesPersonnes = Controleur.selectAllPersonne(filtre); 
        Object [][] matrice = new Object[lesPersonnes.size()][6];
		int i = 0; 
		for (Personne unePersonne : lesPersonnes) {
            matrice[i][0] = unePersonne.getIdpersonne();
            matrice[i][1] = unePersonne.getNom();
            matrice[i][2] = unePersonne.getPrenom();
            matrice[i][3] = unePersonne.getEmail();
            matrice[i][4] = unePersonne.getTel();
            matrice[i][5] = unePersonne.getRole();
			i++;
		}
		return matrice;
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        btEnregistrer = new JButton("Enregistrer");
        gbc.gridx = 0;
        gbc.gridy = 0;
        btEnregistrer.addActionListener(this);
        panel.add(btEnregistrer, gbc);

        btAnnuler = new JButton("Annuler");
        gbc.gridx = 1;
        gbc.gridy = 0;
        btAnnuler.addActionListener(this);
        panel.add(btAnnuler, gbc);

        btSupprimer = new JButton("Supprimer");
        btSupprimer.setEnabled(false);
        gbc.gridx = 2;
        gbc.gridy = 0;
        btSupprimer.addActionListener(this);
        panel.add(btSupprimer, gbc);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.btAnnuler) {
			this.viderChamps();
            this.btSupprimer.setEnabled(false);
            this.btEnregistrer.setText("Enregistrer");

            // Déselectionner la ligne sélectionnée
            tablePersonne.clearSelection();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            boolean ok = true;
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String email = this.txtEmail.getText();
            String tel = this.txtTel.getText();
            String role = this.cbRole.getSelectedItem().toString();
            String mdp = this.txtMdp.getText();

            if (nom.equals("") || prenom.equals("") || email.equals("") || tel.equals("") || mdp.equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                ok = false;
            } else if (Controleur.selectWherePersonne(nom, prenom, email, tel, role) != null) {
                JOptionPane.showMessageDialog(this, "Cette personne existe déjà");
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
                //on enregistre le new personne dans la base 
                Personne unePersonne = new Personne(nom, prenom, email, mdp, tel, role);
                Controleur.ajouterPersonne(unePersonne);
			
				//récupération de l'ID donné par mysql 
				unePersonne = Controleur.selectWherePersonne(nom, prenom, email, tel, role);
			
				JOptionPane.showMessageDialog(this, "Personne inséré avec succés dans la BDD");

				//insertion dans l'affichage graphique 
				Object ligne[]= {unePersonne.getIdpersonne(), nom, prenom, email, tel, role, mdp};
				this.unTableau.ajouterLigne(ligne);
				lbPersonne.setText("Nombre de personne disponibles :"+unTableau.getRowCount());
			
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
            String email = this.txtEmail.getText();
            String tel = this.txtTel.getText();
            String role = this.cbRole.getSelectedItem().toString();
            String mdp = this.txtMdp.getText();

			int numLigne = 0;
			numLigne = tablePersonne.getSelectedRow();
			int idpersonne= Integer.parseInt(tablePersonne.getValueAt(numLigne, 0).toString());
			
            //on enregistre le new personne dans la base
            Personne unePersonne = new Personne(idpersonne, nom, prenom, email, mdp, tel, role);
            Controleur.updatePersonne(unePersonne);
            JOptionPane.showMessageDialog(this, "Personne modifié avec succés dans la BDD");
            //insertion dans l'affichage graphique
            Object ligne[]= {idpersonne, nom, prenom, email, tel, role, mdp};
            this.unTableau.modifierLigne(numLigne, ligne);
            lbPersonne.setText("Nombre de personne disponibles :"+unTableau.getRowCount());
            this.viderChamps();
            this.btEnregistrer.setText("Enregistrer");
            this.btSupprimer.setEnabled(false);
		} else if (e.getSource() == this.btSupprimer) {
            int numLigne = 0;
            int idpersonne = 0;
            numLigne = tablePersonne.getSelectedRow();
            idpersonne = Integer.parseInt(tablePersonne.getValueAt(numLigne, 0).toString());
            int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette personne ?", 
                    "Suppression Personne", JOptionPane.YES_NO_OPTION);
            if (reponse == 0) {
                //suppression dans la BDD
                Controleur.deletePersonne(idpersonne);
                //suppression dans l'affichage de la table 
                unTableau.supprimerLigne(numLigne);
                lbPersonne.setText("Nombre de personnes dans la DB :"+unTableau.getRowCount());
                this.btSupprimer.setEnabled(false);
                this.viderChamps();
			    this.btEnregistrer.setText("Enregistrer");
            }

           
        }
    }

    private void updateSupprimerButtonState() {
        int numLigne = tablePersonne.getSelectedRow();
        btSupprimer.setEnabled(numLigne >= 0); // Active le bouton si une ligne est sélectionnée
    }

    private void viderChamps() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtEmail.setText("");
        txtTel.setText("");
        txtMdp.setText("");
        cbRole.setSelectedIndex(0);
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
    
        for (int i = 0; i < tablePersonne.getColumnCount(); i++) {
            tablePersonne.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}