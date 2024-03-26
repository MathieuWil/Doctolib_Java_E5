package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel
{
	private String entetes []; 
	private Object donnees[][]; 
	
	public Tableau(String[] entetes, Object[][] donnees) {
		super();
		this.entetes = entetes;
		this.donnees = donnees;
	}

    public Tableau(String[] donnees2, String[] entetes2) {
		super();
		this.entetes = entetes2;
		this.donnees = new Object[0][0];
    }

    @Override
	public int getRowCount() {
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.donnees[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return this.entetes[column];
	}
	public void ajouterLigne(Object ligne[]) {
		Object matrice [][] = new Object [this.donnees.length+1][this.entetes.length];
		//on recopie les donnees dans la matrice 
		for (int i=0; i <this.donnees.length; i++) {
			matrice [i] = this.donnees[i]; 
		}
		//on ajoute la ligne dans la matrice 
		matrice [this.donnees.length] = ligne;
		//on ecrase les données par la nouvelle matrice
		this.donnees = matrice; 
		//on applique les changements 
		this.fireTableDataChanged();
	}
	public void supprimerLigne (int numLigne) {
		Object matrice [][] = new Object [this.donnees.length-1][this.entetes.length];
		//on recopie les donnees dans la matrice 
		int j = 0; 
		for (int i=0; i <this.donnees.length; i++) {
			if(i != numLigne) {
					matrice [j] = this.donnees[i]; 
					j++;
			}
		}
		//on ecrase les données par la nouvelle matrice
		this.donnees = matrice; 
		//on applique les changements 
		this.fireTableDataChanged();
	}
	public void modifierLigne (int numLigne,Object ligne[] ) {
		Object matrice [][] = new Object [this.donnees.length][this.entetes.length];
		//on recopie les donnees dans la matrice 
		int j = 0; 
		for (int i=0; i <this.donnees.length; i++) {
			if(i != numLigne) {
					matrice [j] = this.donnees[i]; 
					j++;
			}else {
				matrice [j] = ligne; 
				j++;
			}
		}
		//on ecrase les données par la nouvelle matrice
		this.donnees = matrice; 
		//on applique les changements 
		this.fireTableDataChanged();
	}
	public void setDonnees (Object matrice [][]) {
		this.donnees = matrice ; 
		this.fireTableDataChanged();
	}
	
}