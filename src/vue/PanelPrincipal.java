package vue;

import java.awt.Color;

import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {

	public PanelPrincipal(Color uneCouleur) {
		this.setBackground(uneCouleur);
		this.setBounds(40, 100, 900, 400);
		this.setLayout(null);
		this.setVisible(false);
	}
}