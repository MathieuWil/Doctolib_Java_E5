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
import controleur.RendezVous;
import controleur.Tableau;

public class PanelRendezVous extends PanelPrincipal implements ActionListener, KeyListener {

    private JTextField txtDaterdv, txtHeure;
    private JButton btEnregistrer, btAnnuler, btSupprimer;
    private JComboBox<String> etat;
    private static JComboBox<String> cbPatient, cbMedecin;

    private JTable tableRdv; 
	private JScrollPane uneScroll; 
	private Tableau unTableau;

    private JLabel lbRdv;

    private JPanel panelRech = new JPanel();
    private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");

    public PanelRendezVous() {
        super(new Color(4, 174, 197));

        // Section 0: Titre
        JPanel titrePanel = createTitrePanel();
        titrePanel.setBackground(new Color(4, 174, 197));

        // Section 1: Patient Information Form
        JPanel rdvInfoPanel = createRdvInfoPanel();
        rdvInfoPanel.setBackground(new Color(4, 174, 197));

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

        // Section 1: Rdv Information Form
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        add(rdvInfoPanel, gbc);

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
        JLabel titleLabel = new JLabel("<html><u><b>Gestion des rendez-vous</b></u></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alignement central du texte
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createRdvInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblDaterdv = new JLabel("Date du rendez-vous : ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblDaterdv, gbc);

        txtDaterdv = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtDaterdv, gbc);

        JLabel lblHeure = new JLabel("Heure du rendez-vous : ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblHeure, gbc);

        txtHeure = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtHeure, gbc);

        JLabel lblEtat = new JLabel("État du rendez-vous : ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblEtat, gbc);

        etat = new JComboBox<String>();
        etat.addItem("En attente");
        etat.addItem("Confirmé");
        etat.addItem("Annulé");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(etat, gbc);

        JLabel lblPatient = new JLabel("Patient : ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblPatient, gbc);

        cbPatient = new JComboBox<>(Controleur.selectAllPatients());
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(cbPatient, gbc);

        JLabel lblMedecin = new JLabel("Médecin : ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblMedecin, gbc);

        cbMedecin = new JComboBox<>(Controleur.selectAllMedecins());
        gbc.gridx = 1;
        gbc.gridy = 4;
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

        String entetes[] = {"ID", "Date", "Heure", "État", "Patient", "Médecin"};
        this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
		this.tableRdv = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.tableRdv); 
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

        lbRdv = new JLabel("Nombre de medecins disponibles : "+unTableau.getRowCount());
        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 1;
        panel.add(lbRdv, gbc);

        // Interdiction de déplacer les colonnes
        this.tableRdv.getTableHeader().setReorderingAllowed(false);

        //ajout d'un MouseListener pour suppression et modification. 
		this.tableRdv.addMouseListener(new MouseListener() {
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
				int idrendezvous = 0; 
				if (e.getClickCount()>=2) {
					numLigne = tableRdv.getSelectedRow(); 
					idrendezvous= Integer.parseInt(tableRdv.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce rendez-vous ?", 
							"Suppression rendez-vous", JOptionPane.YES_NO_OPTION); 
					if (reponse == 0) {
						//suppression dans la BDD
						Controleur.deleteRdv(idrendezvous);
						//suppression dans l'affichage de la table 
						unTableau.supprimerLigne(numLigne);
						lbRdv.setText("Nombre de rendez-vous pris :"+unTableau.getRowCount());
					}
				}
				else {
					numLigne = tableRdv.getSelectedRow();
                    idrendezvous = Integer.parseInt(tableRdv.getValueAt(numLigne, 0).toString());
                    String date = tableRdv.getValueAt(numLigne, 1).toString();
                    String heure = tableRdv.getValueAt(numLigne, 2).toString();
                    String etatRdv = tableRdv.getValueAt(numLigne, 3).toString();
                    String patientRdv = tableRdv.getValueAt(numLigne, 4).toString();
                    String medecinRdv = tableRdv.getValueAt(numLigne, 5).toString();


					//remplissage du formulaire
                    txtDaterdv.setText(date);
                    txtHeure.setText(heure);
                    etat.setSelectedItem(etatRdv);
                    cbPatient.setSelectedItem(patientRdv);
                    cbMedecin.setSelectedItem(medecinRdv);

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
        ArrayList<RendezVous> lesRendezVous = Controleur.selectAllRDV(filtre); 
		Object [][] matrice = new Object[lesRendezVous.size()][6];
		int i = 0; 
		for (RendezVous unRendezVous : lesRendezVous) {
			matrice[i][0] = unRendezVous.getIdrendezvous();
            matrice[i][1] = unRendezVous.getDaterdv();
            matrice[i][2] = unRendezVous.getHeure();
            matrice[i][3] = unRendezVous.getEtat();
            matrice[i][4] = unRendezVous.getIdpatient();
            matrice[i][5] = unRendezVous.getIdmedecin();
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
        btSupprimer.setEnabled(false);
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
            this.btSupprimer.setEnabled(false);
            this.btEnregistrer.setText("Enregistrer");

            // Déselectionner la ligne sélectionnée
            tableRdv.clearSelection();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            boolean ok = true;
            String date = this.txtDaterdv.getText();
            String heure = this.txtHeure.getText();
            String etatRdv = this.etat.getSelectedItem().toString();
            String chaine = this.cbPatient.getSelectedItem().toString(); 
            String tab [] = chaine.split(" ");
            int patientRdv = Integer.parseInt(tab[0]);

            chaine = this.cbMedecin.getSelectedItem().toString(); 
            tab  = chaine.split(" ");
            int medecinRdv = Integer.parseInt(tab[0]);

            if (date.equals("") || heure.equals("")) {
                ok = false;
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (Controleur.selectWhereRdv(date, heure, etatRdv, patientRdv, medecinRdv) != null) {
                ok = false;
                JOptionPane.showMessageDialog(this, "Ce rendez-vous existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        
            //on vérifie les données avant insertion dans la base 
            if (ok) {
                //on enregistre le new personne dans la base 
                RendezVous unRendezVous = new RendezVous(date, heure, etatRdv, patientRdv, medecinRdv);
                Controleur.ajouterRdv(unRendezVous);
			
				//récupération de l'ID donné par mysql 
				unRendezVous = Controleur.selectWhereRdv(date, heure, etatRdv, patientRdv, medecinRdv);
			
				JOptionPane.showMessageDialog(this, "Rendez-vous inséré avec succés dans la BDD");

				//insertion dans l'affichage graphique 
				Object ligne[]= {unRendezVous.getIdrendezvous(), date, heure, etatRdv, patientRdv, medecinRdv};
				this.unTableau.ajouterLigne(ligne);
				lbRdv.setText("Nombre de rendez-vous disponibles :"+unTableau.getRowCount());
			
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
            Boolean ok = true;
            String date = this.txtDaterdv.getText();
            String heure = this.txtHeure.getText();
            String etatRdv = this.etat.getSelectedItem().toString();
            String chaine = this.cbPatient.getSelectedItem().toString(); 
            String tab [] = chaine.split(" ");
            int patientRdv = Integer.parseInt(tab[0]);

            chaine = this.cbMedecin.getSelectedItem().toString(); 
            tab  = chaine.split(" ");
            int medecinRdv = Integer.parseInt(tab[0]);
            
            int idrendezvous = 0;
			int numLigne = 0;
			numLigne = tableRdv.getSelectedRow();
			idrendezvous= Integer.parseInt(tableRdv.getValueAt(numLigne, 0).toString());
			
    	    //Instanciation d'un Personne 
			RendezVous unRendezVous = new RendezVous(idrendezvous, date, heure, etatRdv, patientRdv, medecinRdv); 
			//modification dans la base de données 
			Controleur.updateRdv(unRendezVous);
			//modification dans l'affichage 
			Object ligne []= {idrendezvous, date, heure, etatRdv, patientRdv, medecinRdv};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectuée");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		} else if (e.getSource() == this.btSupprimer) {
            int numLigne = tableRdv.getSelectedRow();
            int idrendezvous = Integer.parseInt(tableRdv.getValueAt(numLigne, 0).toString());
            int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce rendez-vous ?", "Suppression rendez-vous", JOptionPane.YES_NO_OPTION);
            if (reponse == 0) {
                // Suppression dans la BDD
                Controleur.deleteRdv(idrendezvous);
                // Suppression dans l'affichage de la table
                unTableau.supprimerLigne(numLigne);
                lbRdv.setText("Nombre de rendez-vous pris :" + unTableau.getRowCount());
                this.btSupprimer.setEnabled(false);
                this.viderChamps();
			    this.btEnregistrer.setText("Enregistrer");
                this.btSupprimer.setEnabled(false);
                this.viderChamps();
			    this.btEnregistrer.setText("Enregistrer");
            }
        }
    }

    private void updateSupprimerButtonState() {
        int numLigne = tableRdv.getSelectedRow();
        btSupprimer.setEnabled(numLigne >= 0); // Active le bouton si une ligne est sélectionnée
    }

    private void viderChamps() {
        txtDaterdv.setText("");
        txtHeure.setText("");
        etat.setSelectedIndex(0);
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

        for (int i = 0; i < tableRdv.getColumnCount(); i++) {
            tableRdv.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}