package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.DoctolibJava;
import controleur.Personne;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {
    
    private JPanel panelConnexion;
    private JLabel lblLogo, lblTitre, lblLogin, lblMdp;
    private JTextField txtLogin;
    private JPasswordField txtMdp;
    private JButton btnConnexion, btnAnnuler, btnQuitter;
    
    public VueConnexion() {
        this.setTitle("Doctolib");
        this.setResizable(false);
        this.setBounds(500, 200, 400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    
        panelConnexion = new JPanel();
        panelConnexion.setBounds(0, 0, 400, 300);
        panelConnexion.setLayout(null);
        panelConnexion.setBackground(new Color(4, 174, 197));
        this.add(panelConnexion);
    
        lblLogo = new JLabel(new ImageIcon("src\\img\\Doctolib.png"));
        lblLogo.setBounds(-10, -55, 400, 200);
        panelConnexion.add(lblLogo);
    
        lblTitre = new JLabel("Connexion à mon espace Doctolib :");
        lblTitre.setBounds(100, 80, 200, 30);
        panelConnexion.add(lblTitre);
    
        lblLogin = new JLabel("Login : ");
        lblLogin.setBounds(50, 120, 100, 30);
        panelConnexion.add(lblLogin);
    
        txtLogin = new JTextField();
        txtLogin.setBounds(150, 120, 200, 30);
        panelConnexion.add(txtLogin);
        txtLogin.setText("blucbert@gmail.com");
    
        lblMdp = new JLabel("Mot de passe : ");
        lblMdp.setBounds(50, 160, 100, 30);
        panelConnexion.add(lblMdp);
    
        txtMdp = new JPasswordField();
        txtMdp.setBounds(150, 160, 200, 30);
        panelConnexion.add(txtMdp);
        txtMdp.setText("Superb@pt95!");
    
        btnConnexion = new JButton("Connexion");
        btnConnexion.setBounds(50, 200, 100, 30);
        panelConnexion.add(btnConnexion);
        btnConnexion.addActionListener(this);

        btnAnnuler = new JButton("Annuler");
        btnAnnuler.setBounds(150, 200, 100, 30);
        panelConnexion.add(btnAnnuler);
        btnAnnuler.addActionListener(this);

        btnQuitter = new JButton("Quitter");
        btnQuitter.setBounds(250, 200, 100, 30);
        panelConnexion.add(btnQuitter);
        btnQuitter.addActionListener(this);

        // on rend les champs de saisie écoutables
        txtLogin.addKeyListener(this);
        txtMdp.addKeyListener(this);
        
    
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Gérer l"événement de frappe de touche si nécessaire
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Gérer l"événement de pression de touche si nécessaire
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Gérer l"événement de libération de touche si nécessaire
    }

    public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == btnConnexion) {
            traitement();
        }
        else if (e.getSource() == btnAnnuler) {
            txtLogin.setText("");
            txtMdp.setText("");
        }
        else if (e.getSource() == btnQuitter) {
            System.exit(0);
        }
	}

	public void traitement () {
        String login = this.txtLogin.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		
		//on vérifie dans la base de données à travers le modèle
		Personne unePersonne = Controleur.verifConnexion(login, mdp); 
		if (unePersonne == null) {
			JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants!");
			this.txtLogin.setText("");
			this.txtMdp.setText("");
		} else {
			//on lance la vue générale et on réduit la vue connexion 
			DoctolibJava.rendreVisibleGenerale(true, unePersonne);
			DoctolibJava.rendreVisibleConnexion(false);
		}
    }
}