package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.DoctolibJava;
import controleur.Personne;

public class VueGenerale extends JFrame implements ActionListener {
    private JButton btMedecins = createStyledButton("Médecins");
    private JButton btPatients = createStyledButton("Patients");
    private JButton btRendezVous = createStyledButton("Rendez-Vous");
    private JButton btAdministrateurs = createStyledButton("Administrateurs");
    private JButton btPrescriptions = createStyledButton("Prescriptions");
    private JButton btProfil = createStyledButton("Profil");
    private JButton btQuitter = createStyledButton("Quitter");

    private JPanel panelMenu = new JPanel();
    private static PanelProfil unPanelProfil;
    private static PanelMedecins unPanelMedecins = new PanelMedecins();
    private static PanelPatients unPanelPatients = new PanelPatients();
    private static PanelRendezVous unPanelRendezVous = new PanelRendezVous();
    private static PanelAdministrateurs unPanelAdministrateurs = new PanelAdministrateurs();
    private static PanelPrescriptions unPanelPrescriptions = new PanelPrescriptions();

    public VueGenerale(Personne unePersonne) {
        this.setTitle("Doctolib");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1000, 600);
        this.getContentPane().setBackground(new Color(4, 174, 197));
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        // Installation of the panel menu
        panelMenu.setLayout(new GridLayout(1, 6, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelMenu.setBackground(new Color(4, 174, 197));

        // Add buttons to the menu panel
        panelMenu.add(btProfil);
        panelMenu.add(btPatients);
        panelMenu.add(btMedecins);
        panelMenu.add(btRendezVous);
        panelMenu.add(btPrescriptions);
        panelMenu.add(btAdministrateurs);
        panelMenu.add(btQuitter);

        // Add the menu panel to the frame
        this.add(panelMenu, BorderLayout.NORTH);

        // Initialize action listeners for buttons
        this.btProfil.addActionListener(this);
        this.btMedecins.addActionListener(this);
        this.btPatients.addActionListener(this);
        this.btRendezVous.addActionListener(this);
        this.btAdministrateurs.addActionListener(this);
        this.btPrescriptions.addActionListener(this);
        this.btQuitter.addActionListener(this);

        // Initialize panels
        unPanelProfil = new PanelProfil(unePersonne);

        // Add panels to the frame
        this.add(unPanelProfil, BorderLayout.CENTER);
        this.add(unPanelMedecins, BorderLayout.CENTER);
        this.add(unPanelPatients, BorderLayout.CENTER);
        this.add(unPanelRendezVous, BorderLayout.CENTER);
        this.add(unPanelPrescriptions, BorderLayout.CENTER);
        this.add(unPanelAdministrateurs, BorderLayout.CENTER);

        this.setVisible(false);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    public void afficher(int choix) {
        // Hide all panels
        unPanelProfil.setVisible(false);
        unPanelMedecins.setVisible(false);
        unPanelPatients.setVisible(false);
        unPanelRendezVous.setVisible(false);
        unPanelPrescriptions.setVisible(false);
        unPanelAdministrateurs.setVisible(false);

        // Show the selected panel
        switch (choix) {
            case 1:
                unPanelProfil.setVisible(true);
                break;
            case 2:
                unPanelMedecins.setVisible(true);
                break;
            case 3:
                unPanelPatients.setVisible(true);
                break;
            case 4:
                unPanelRendezVous.setVisible(true);
                break;
            case 5:
                unPanelPrescriptions.setVisible(true);
                break;
            case 6:
                unPanelAdministrateurs.setVisible(true);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == btQuitter) {
            DoctolibJava.rendreVisibleGenerale(false, null);
            DoctolibJava.rendreVisibleConnexion(true);
        } else if (e.getSource() == btProfil) {
            afficher(1);
        } else if (e.getSource() == btMedecins) {
            afficher(2);
        } else if (e.getSource() == btPatients) {
            afficher(3);
        } else if (e.getSource() == btRendezVous) {
            afficher(4);
        } else if (e.getSource() == btPrescriptions) {
            afficher(5);
        } else if (e.getSource() == btAdministrateurs) {
            afficher(6);
        }
    }
}