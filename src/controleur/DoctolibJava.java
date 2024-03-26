package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class DoctolibJava {

    private static VueGenerale uneVueGenerale;
    private static VueConnexion uneVueConnexion;

    public static void main(String[] args) {
        uneVueConnexion = new VueConnexion();
    }

    public static void rendreVisibleConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}

	public static void rendreVisibleGenerale (boolean action, Personne unePersonne) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(unePersonne); 
			uneVueGenerale.setVisible(true);
		} else {
			uneVueGenerale.dispose();
		}
	}

    public static void quitterApplication() {
        System.exit(0);
    }
}
